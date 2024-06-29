<!-- // Above is only text sending.

<template>
  <div class="chat-box">
    <div class="chat-messages" ref="messageContainer">
      <div v-for="(message, index) in messages" :key="index" class="message" :class="{ 'user-message': message.sender === 'user', 'other-message': message.sender !== 'user' }">
        <div class="message-content">
          <template v-if="message.type === 'text'">
            {{ message.text }}
          </template>
          <template v-else-if="message.type === 'image'">
            <img :src="message.src" alt="Image" class="message-image">
          </template>
          <template v-else-if="message.type === 'audio'">
            <audio controls :src="message.src"></audio>
          </template>
          <template v-else-if="message.type === 'video'">
            <video controls :src="message.src" class="message-video"></video>
          </template>
          <span class="message-time">{{ formatTime(message.time) }}</span>
        </div>
      </div>
    </div>
    <div class="chat-input">
      <input type="file" ref="fileInput" style="display: none" @change="handleFileUpload">
      <button @click="showFileDialog">Attach File</button>
      <input type="text" v-model="newMessage" @keyup.enter="sendMessage" placeholder="Type your message...">
      <button @click="sendMessage">Send</button>
      <button @click="toggleEmojiPicker">&#128515;</button>
      <button @click="uploadFile">Upload File</button>
    </div>
    <div class="emoji-picker" v-show="showEmojiPicker">
      <div class="emoji" v-for="emoji in emojis" :key="emoji" @click="insertEmoji(emoji)">{{ emoji }}</div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      messages: [],
      newMessage: '',
      showEmojiPicker: false,
      emojis: ['😊', '😂', '😍', '😎', '🤔', '😜', '🎉', '👍', '❤️', '🔥'], // Example emojis
      selectedFile: null // Store the selected file
    };
  },
  methods: {
    sendMessage() {
      if (this.newMessage.trim() !== '') {
        this.messages.push({
          sender: 'user',
          type: 'text',
          text: this.newMessage,
          time: new Date()
        });
        this.newMessage = '';
        this.scrollToBottom();
      }
    },
    handleFileUpload(event) {
      const file = event.target.files[0];
      this.selectedFile = file; // Store the selected file
      const reader = new FileReader();
      const formData = new FormData();
      formData.append('file', this.selectedFile);

      axios.post('http://localhost:8080/api/upload', formData)
        .then(response => {
          console.log(response.data);
        })
        .catch(error => {
          console.error(error);
        });
        
      reader.onload = () => {
        let messageType;
        if (file.type.startsWith('image')) {
          messageType = 'image';
        } else if (file.type.startsWith('audio')) {
          messageType = 'audio';
        } else if (file.type.startsWith('video')) {
          messageType = 'video';
        } else {
          console.error('Unsupported file type');
          return;
        }
        this.messages.push({
          sender: 'user',
          type: messageType,
          src: reader.result,
          time: new Date()
        });
        this.scrollToBottom();
      };
      reader.readAsDataURL(file);
    },
    uploadFile() {
      if (!this.selectedFile) {
        console.error('No file selected');
        return;
      }

      const formData = new FormData();
      formData.append('file', this.selectedFile);

      axios.post('http://localhost:8080/api/upload', formData)
        .then(response => {
          console.log(response.data);
        })
        .catch(error => {
          console.error(error);
        });
    },
    scrollToBottom() {
      this.$refs.messageContainer.scrollTop = this.$refs.messageContainer.scrollHeight;
    },
    toggleEmojiPicker() {
      this.showEmojiPicker = !this.showEmojiPicker;
    },
    insertEmoji(emoji) {
      this.newMessage += emoji;
    },
    showFileDialog() {
      this.$refs.fileInput.click();
    },
    formatTime(time) {
      const options = { hour: 'numeric', minute: 'numeric', hour12: true };
      return new Intl.DateTimeFormat('en-US', options).format(time);
    }
  }
};
</script>

<style scoped>
/* Chat box styles */
.chat-box {
  border: 1px solid #ccc;
  border-radius: 5px;
  overflow: hidden;
  display: flex;
  flex-direction: column;
  height: 400px;
}

/* Chat messages styles */
.chat-messages {
  overflow-y: auto;
  flex-grow: 1;
  padding: 10px;
}

.message {
  margin-bottom: 10px;
  padding: 10px;
  border-radius: 5px;
  max-width: 70%;
}

.user-message {
  align-self: flex-end;
  background-color: #007bff;
  color: white;
}

.other-message {
  align-self: flex-start;
  background-color: #f0f0f0;
}

.message-content {
  display: inline-block;
}

.message-time {
  font-size: 0.8em;
  margin-left: 5px;
}

/* Chat input styles */
.chat-input {
  display: flex;
  align-items: center;
  padding: 10px;
  border-top: 1px solid #ccc;
}

.chat-input input[type="file"] {
  display: none;
}

.chat-input input[type="text"] {
  flex-grow: 1;
  padding: 10px;
  border: none;
  border-radius: 5px;
  margin-right: 10px;
}

.chat-input button {
  padding: 10px 20px;
  border: none;
  border-radius: 5px;
  background-color: #007bff;
  color: white;
  cursor: pointer;
}

.chat-input button:hover {
  background-color: #0056b3;
}

.emoji-picker {
  display: flex;
  flex-wrap: wrap;
  padding: 10px;
  background-color: #f0f0f0;
}

.emoji-picker .emoji {
  cursor: pointer;
  font-size: 1.5em;
  margin: 5px;
}
</style> -->

<template>
  <div class="chat-box">
    <!-- Chat messages -->
    <div class="chat-messages">
      <div v-for="(message, index) in messages" :key="index" class="message">
        <div class="message-content">
          <!-- Display audio content -->
          <audio v-if="message.type === 'audio'" controls :src="message.content"></audio>
          <!-- Display image content -->
          <img v-else-if="message.type === 'image'" :src="message.content" alt="Image" class="message-image">
          <!-- Display text content -->
          <span v-else>{{ message.content }}</span>
          <span class="message-time">{{ formatTime(message.time) }}</span>
        </div>
      </div>
    </div>
    
    <!-- Chat input -->
    <div class="chat-input">
      <input type="text" v-model="newMessage" placeholder="Type your message...">
      <button @click="sendMessage('text')">Send Text</button>
      <button @click="sendMessage('audio')">Send Audio</button>
      <button @click="sendMessage('image')">Send Image</button>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      messages: [],
      newMessage: ''
    };
  },
  methods: {
    sendMessage(type) {
      if (this.newMessage.trim() !== '') {
        // Determine content type based on user input
        let messageType = 'text';
        if (type === 'audio') {
          messageType = 'audio';
        } else if (type === 'image') {
          messageType = 'image';
        }

        // Push message to the messages array
        this.messages.push({
          sender: 'user',
          type: messageType,
          content: this.newMessage,
          time: new Date()
        });

        // Send plain URL to the backend
        axios.post('http://localhost:8080/api/messages', {
          content: this.newMessage,
          type: type
        })
        .then(response => {
          console.log('Message sent to backend:', response.data);
        })
        .catch(error => {
          console.error('Error sending message to backend:', error);
        });

        // Clear the message input
        this.newMessage = '';
      }
    },
    formatTime(time) {
      // Format message time (implement as needed)
    }
  }
};
</script>

<style scoped>
/* Overall Chat Box Styling */
.chat-box {
  border: 1px solid #ddd;
  border-radius: 5px;
  overflow: hidden;
  display: flex;
  flex-direction: column;
  height: 650px; /* Adjust height as needed */
  background: linear-gradient(to right, #f1f4f8, #fff); /* Light gradient background */
  box-shadow: 0 1px 2px rgba(0, 0, 0, 0.1); /* Subtle box shadow for depth */
  padding: 10px;
}

/* Chat Messages Styling */
.chat-messages {
  overflow-y: auto;
  flex-grow: 1;
  padding: 10px;
}

.message {
  margin-bottom: 10px;
  padding: 10px;
  border-radius: 5px;
  max-width: 70%;
  background-color: #fff; /* Consistent background for messages */
  border: 1px solid #ddd; /* Light border for separation */
  position: relative; /* Enable message-time positioning */
}

.user-message {
  align-self: flex-end;
  background-color: linear-gradient(to right, #39b3c9, #35f0bb); /* Light blue for user messages */
  color: #333;
}

.other-message {
  align-self: flex-start;
  background-color: #f5f5f5; /* Light gray for other messages */
  color: #333;
}

.message-content {
  display: inline-block;
}

.message-time {
  font-size: .9em;
  position: absolute;
  bottom: 5px;
  right: 10px;
  color: #000000;
}

/* Chat Input Styling */
.chat-input {
  display: flex;
  align-items: center;
  padding: 10px;
  border-top: 1px solid #ddd;
}

.chat-input input[type="text"] {
  flex-grow: 1;
  padding: 10px;
  border: none;
  border-radius: 5px;
  margin-right: 10px;
  font-size: 14px;
  outline: none; /* Remove default outline */
}

.chat-input button {
  padding: 10px 20px;
  border: none;
  border-radius: 5px;
  background-color: #5fd0ec; /* Instagram blue */
  color: white;
  cursor: pointer;
  transition: background-color 0.2s ease;
}

.chat-input button:hover {
  background-color: #2beae3; /* Darker blue on hover */
}

</style>
