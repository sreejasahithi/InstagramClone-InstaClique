<template>
  <div class="post">
    <h3>{{ displayPost.title }}</h3>
    <p>{{ displayPost.description }}</p>
    <img :src="displayPost.contentUrl" alt="Post Image">
    <p>Author: {{ displayPost.author }}</p>
    <p>Created At: {{ displayPost.createdAt }}</p>
  </div>
</template>

<script>
export default {
  props: {
    post: {
      type: Object,
      required: true,
      validator: (value) => {
        return value.contentType === 'image'; // Validate that the post is an image
      }
    }
  },
  computed: {
    displayPost() {
      return this.post;
    }
  }
};
</script>

<style scoped>
.post {
  margin-bottom: 20px;
  padding: 10px;
  border: 1px solid #ddd;
  border-radius: 5px;
  background-color: #fff;
}
</style>
