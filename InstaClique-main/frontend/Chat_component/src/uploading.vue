<template>
    <div id="app">
        <input type="text" v-model="contentType" placeholder="Enter Content Type...">
        <input type="file" ref="fileInput" accept="image/*, video/*" @change="handleFileUpload">
        <button @click="submit">Upload</button>
        <button @click="displayMedia">View Posts</button>
        <div v-if="responseMessage">{{ responseMessage }}</div>
        <div v-if="mediaData">
            <div v-for="(media, postId) in mediaData" :key="postId" class="allposts">
                <template v-if="media.type === 'image'">
                    <img :src="media.data" alt="Post Image" @click="showDeleteButton(postId)">
                </template>
                <template v-else-if="media.type === 'video'">
                    <video :src="media.data" controls @click="showDeleteButton(postId)"></video>
                </template>
                <button v-if="postId === selectedPostId" @click="deleteMedia(postId)">Delete</button>
            </div>
        </div>
    </div>
</template>

<!-- Uncomment if not using a CDN -->
<!-- <script src="https://cdn.jsdelivr.net/npm/vue@2.6.14/dist/vue.js"></script> -->

<script>
    // import Vue from 'vue'; // Import Vue if not using a CDN

    export default {
        data() {
            return {

                contentType: '',
                file: null,
                responseMessage: '',
                mediaData: null,
                selectedPostId: null
            };
        },
        methods: {
                handleFileUpload(event) {
                    this.file = event.target.files[0];
                },
                submit() {
                    if (!this.contentType || !this.file) {
                        console.error('All fields are required');
                        return;
                    }

                    let formData = new FormData();
                   // formData.append('userId', this.userId);
                    formData.append('content_type', this.contentType);
                    if(this.contentType==="image"){
                    	 formData.append('image', this.file); 
                         formData.append('video', null);
                    }
                    if(this.contentType==="video"){
                   	 formData.append('image', null); 
                        formData.append('video', this.file);
                   }
                   
                    console.log(this.file);

                    fetch('http://localhost:8080/insertPost', {
                        method: 'POST',
                        body: formData
                    })
                    .then(response => response.text())
                    .then(data => {
                        console.log(data);
                        this.responseMessage = data;
                    })
                    .catch(error => console.error('Error:', error));
                },
                displayMedia() {
                    fetch("http://localhost:8080/getPhoto")
                    .then(response => {
                        if (!response.ok) {
                            console.log("HELLO");
                            throw new Error('Network response was not ok');
                        }
                        return response.json();
                    })
                    .then(data => {
                        this.mediaData = {};
                        for (let postId in data) {
                            let mediaType = data[postId].type;
                            console.log(mediaType);
                            this.mediaData[postId] = {
                                type: mediaType,
                                data: `data:${mediaType === 'image' ? 'image/jpeg' : 'video/mp4'};base64,${data[postId].data}`
                            };
                        }
                    })
                    .catch(error => console.error('Error:', error));
                },
                showDeleteButton(postId) {
                    this.selectedPostId = postId;
                },
                deleteMedia(postId) {
                    fetch(`http://localhost:8080/deletePost/${postId}`, {
                        method: 'POST'
                    })
                    .then(response => response.text())
                    .then(data => {
                        console.log(data);
                        // After deleting, remove the media from the mediaData object
                        Vue.delete(this.mediaData, postId);
                    })
                    .catch(error => console.error('Error:', error));
                }
            }
    };
</script>

<style>
    /* Style the container for the posts */
#app {
  text-align: center; /* Center the content horizontally */
  align-items: justify;
  margin: 20px auto; /* Add some margin for better spacing */
  max-width: 800px; /* Set a maximum width for responsiveness */
}
.allposts{
    position:relative;
    left:250px;
}
/* Style the input fields */
input[type="text"],
input[type="file"] {
  padding: 10px;
  border: 1px solid #ccc;
  margin: 5px;
  border-radius: 4px;
}

/* Style the submit and display buttons */
button {
  padding: 10px 20px 20px 20px;
  background: linear-gradient(#6bc6f7,#e5cccc); /* Green color */
  color: black;

  border: none;
  border-radius: 10px;
  cursor: pointer;
  margin: 5px;
}

/* Style the submit button on hover */
button:hover {
  background-color: #69f9f9; /* Darker green on hover */
}

/* Style the response message */
.responseMessage {
  font-weight: bold;
  margin-bottom: 10px;
}

</style>
