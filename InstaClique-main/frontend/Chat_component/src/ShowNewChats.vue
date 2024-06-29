<template>
    <div>
      <div v-for="chat in chats" :key="chat.id">
        <!-- Display content -->
        <div v-if="chat.data_type === 'text'">
          <p>{{ chat.from_id }}: {{ decodeBinaryToString(chat.content.data) }}</p>
        </div>
        <div v-else-if="chat.data_type.startsWith('audio')">
          <!-- Render audio player -->
          <audio controls :src="createMultimediaUrl(chat.content.data,chat.content.format)"></audio>
        </div>
        <div v-else-if="chat.data_type.startsWith('video')">
          <!-- Render video player -->
          <video controls :src="createMultimediaUrl(chat.content.data,chat.content.format)"></video>
        </div>
        <div v-else-if="chat.data_type.startsWith('image')">
          <!-- Render image -->
          <img :src="createMultimediaUrl(chat.content.data,chat.content.format)" alt="Image" />
        </div>
        <!-- Display time, from_id, and to_id -->
        <p>{{ chat.time }} - From: {{ chat.from_id }} To: {{ chat.to_id }}</p>
      </div>
    </div>
  </template>
  
  <script>
  import axios from "axios";
  
  export default {
    data() {
      return {
        chats: [],
        From: "2",
        To: "1",
      };
    },
    mounted() {
      this.getChatData();
    },
    methods: {
      getChatData() {
        axios.get(`http://localhost:8080/api/chat/${this.To}/${this.From}`)
          .then(response => {
            this.chats = response.data;
          })
          .catch(error => {
            console.error('Error fetching chat data:', error);
          });
      },
      decodeBinaryToString(binaryData) {
      // Convert binary data to a string using TextDecoder
      const decoder = new TextDecoder();
      const decodedString = decoder.decode(new Uint8Array(atob(binaryData).split('').map(char => char.charCodeAt(0))));
      return decodedString;
    },
    createMultimediaUrl(binaryData, format) {
      // Convert binary data to a blob
      const blob = new Blob([this.base64ToArrayBuffer(binaryData)], { type: format });
      // Create URL object from the blob
      return URL.createObjectURL(blob);
    },
    base64ToArrayBuffer(base64) {
      const binaryString = atob(base64);
      const bytes = new Uint8Array(binaryString.length);
      for (let i = 0; i < binaryString.length; i++) {
        bytes[i] = binaryString.charCodeAt(i);
      }
      return bytes.buffer;
    },
      getMediaUrl(content) {
        // Assuming content is a binary data representing audio, video, or image
        // Convert binary data to blob
        const blob = new Blob([content], { type: content.data_type });
  
        // Create a blob URL
        return URL.createObjectURL(blob);
      },
    }
  };
  </script>
  
  <style>
  /* Add your styles here */
  </style>
  