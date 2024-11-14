import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios';
import { useAuthStore } from './auth';
import { useRouter } from 'vue-router';

export const useBlogStore = defineStore('blogStore', () => {
  const apiUrl = import.meta.env.VITE_API_BASE_URL;

  const popularBlogs = ref([]);
  const loadingPopularBlogs = ref(false)
  const popularBlogsError = ref(null)

  const latestBlogs = ref([]);
  const loadingLatestBlogs = ref(false)
  const latestBlogsError = ref(null)

  const userBlogs = ref([])
  const userBlogsMsg = ref('')

  const blog = ref({})
  const loadingBlog = ref(false)
  const blogLoadingError = ref(null)
  
  const blogSubmitSuccess = ref(false)

  const auth = useAuthStore();
  const router = useRouter()

  function fetchPopularBlogs() {
    loadingPopularBlogs.value = true;

    axios.get(`${apiUrl}/popularblogs`)
    .then(response => {
      
      popularBlogs.value = response.data
      popularBlogsError.value = null
      loadingPopularBlogs.value = false
    })
    .catch(error => {
      popularBlogsError.value = error
      console.error("There was an error fetching the popular blogs!", error);
    });
  }

  function fetchLatestBlogs() {
    loadingLatestBlogs.value = true;

    axios.get(`${apiUrl}/latestblogs`)
    .then(response => {
      
      latestBlogs.value = response.data
      loadingLatestBlogs.value = false;
      latestBlogsError.value = null
    })
    .catch(error => {
      latestBlogsError.value = error
      console.error("There was an error fetching the latest blogs!", error);
    });
  }

  async function fetchBlog(id) {
    loadingBlog.value = true
    try {      
      const response = await axios.get(`${apiUrl}/blog/${id}`);      
      
      blog.value = response.data;
      blogLoadingError.value = null
      loadingBlog.value = false
    } catch (error) {      
      blogLoadingError.value = error;
      console.error("There was an error fetching the blog!", error);
    }
  }

  function fetchBlogsByUser() {
    axios.get(`${apiUrl}/blogsbyuser/${auth.accountId}`, {
      auth:{
        username: auth.username,
        password: auth.password
      }
    })
    .then(response => {
      
      userBlogs.value = response.data
      if(response.data.length === 0){
        userBlogsMsg.value = 'You have no blogs!'
      }
    })
    .catch(error => {
      console.error("There was an error fetching the user blogs!", error);
    });
  }

  const submitBlog = async (blog) => {
    if(!auth.isLoginSuccess){
      router.push({name: 'login'})
    }
    else{
      try {
        const response = await axios.post(`${apiUrl}/blog`, blog, {
          auth:{
            username: auth.username,
            password: auth.password
          }
        });        
        if (response.status === 200) {
          latestBlogs.value.push(response.data)
        }        
      } catch (err) {
        console.error(err);
        
      }
    }
  };

  const updateBlog = async (blog) => {
    if(!auth.isLoginSuccess){
      router.push({name: 'login'})
    }
    else{
      try {
        const response = await axios.put(`${apiUrl}/blog`, blog, {
          auth:{
            username: auth.username,
            password: auth.password
          }
        });        
        if (response.status === 200) {
          // latestBlogs.value.push(response.data)
          console.log(response.data);
          
        }        
      } catch (err) {
        console.error(err);
        
      }
    }
  };

  async function deleteBlogById(id) {
    try {      
      const response = await axios.delete(`${apiUrl}/blog/${id}`, {
        auth:{
          username: auth.username,
          password: auth.password
        }
      });
      console.log(response.data);
      
      const indexUB = userBlogs.value.findIndex(b => b.id === id)
      const indexPB = popularBlogs.value.findIndex(b => b.id === id)
      const indexLB = latestBlogs.value.findIndex(b => b.id === id)    

      if(indexUB !== -1){
        userBlogs.value.splice(indexUB, 1)
      }
      if(indexPB !== -1){
        popularBlogs.value.splice(indexPB, 1)
      }

      if(indexLB !== -1){
        latestBlogs.value.splice(indexLB, 1)
      }
      
    } catch (error) {
      console.error("There was an error deleting the blog!", error);
    }    
  }

  const handleLike = async (blogId, accId) => {
    const likeDto = {
      blog: {id: blogId},
      account: {id: accId}
    }

    if(!auth.isLoginSuccess){
      router.push({name: 'login'})
    }
    else{
      try {
        const response = await axios.post(`${apiUrl}/handlelike`, likeDto, {
          auth:{
            username: auth.username,
            password: auth.password
          }
        });        
        if (response.status === 200) {

          if(response.data >0){
            blog.value.likeCount.count++;
            const likes = likeDto;
            blog.value.likes.push(likes)
          }
          else{
            blog.value.likeCount.count--;

            const index = blog.value.likes.findIndex(item => item.account.id === auth.accountId);

            if(index !== -1){
              blog.value.likes.splice(index, 1)
            }
          }
        }        
      } catch (err) {
        console.error(err);
        
      }
    }
  };

  const addComment = async (blogId, accId, commentText) => {    
    const commentDto = {
      blog: {id: blogId},
      description: commentText,
      account: {id: accId, fullName: auth.fullName}
    }

    if(!auth.isLoginSuccess){
      router.push({name: 'login'})
    }
    else{
      try {
        const response = await axios.post(`${apiUrl}/comment`, commentDto, {
          auth:{
            username: auth.username,
            password: auth.password
          }
        });        
        if (response.status === 200) {

          blog.value.comments.push(response.data)
          blog.value.commentCount.count++;          
        }        
      } catch (err) {
        console.error(err);        
      }
    }
  };
  
  //update comment
  const updateComment = async (blogId, comment, accId, commentText) => {
    
    const commentDto = {
      id: comment.id,
      blog: {id: blogId},
      description: commentText,
      account: {id: accId, fullName: auth.fullName}
    }

    if(!auth.isLoginSuccess){
      router.push({name: 'login'})
    }
    else{
      try {
        const response = await axios.put(`${apiUrl}/comment`, commentDto, {
          auth:{
            username: auth.username,
            password: auth.password
          }
        });        
        if (response.status === 200) {
          const index = blog.value.comments.findIndex(c => c.id === comment.id)
          if(index !== -1){
            const comment = response.data
            blog.value.comments.splice(index, 1, comment)
          }
          //      
        }        
      } catch (err) {
        console.error(err);        
      }
    }
  };
  //delete comment

  async function deleteCommentById(commentId, blogId) {
    const commentDto = {
      id: commentId,
      blog: {id: blogId},
      account: {id: auth.accountId, fullName: auth.fullName}
    }
    
    try {
      const response = await axios.delete(`${apiUrl}/comment`, {data: commentDto,
        auth:{
          username: auth.username,
          password: auth.password
        }
      });
      console.log(response.data);

      const index = blog.value.comments.findIndex(c => c.id === commentId)
      if(index !== -1){
        blog.value.comments.splice(index, 1);
        blog.value.commentCount.count--;
      }

    } catch (error) {
      console.error("There was an error deleting the comment!", error);
    }    
  };

  return {popularBlogs, loadingPopularBlogs, popularBlogsError, latestBlogs, loadingLatestBlogs, latestBlogsError, userBlogs, userBlogsMsg, blog, loadingBlog, blogLoadingError, blogSubmitSuccess, fetchPopularBlogs, fetchLatestBlogs, fetchBlogsByUser, fetchBlog, submitBlog, updateBlog, deleteBlogById, handleLike, addComment, updateComment, deleteCommentById}
})
