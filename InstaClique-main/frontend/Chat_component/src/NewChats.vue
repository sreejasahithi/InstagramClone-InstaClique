<template>
  <div class="chat-container">
    <!-- Display chat messages -->
    <div class="chat-messages" ref="chatContainer">
      <div v-for="chat in chats" :key="chat.time" :class="{ 'message': true, 'from': chat.from_id === this.From, 'to': chat.from_id === this.To }">
              <div v-if ="chat.from_id === this.From" class="message-content" @mouseover="showDeleteButton=true" @mouseleave="showDeleteButton=false">
                <div v-if="chat.data_type === 'text'" class="message-bubble">
                  <p> {{ decodeBinaryToString(chat.content.data) }}</p>
                </div>
                <div v-else-if="chat.data_type.startsWith('audio')" class="message-bubble">
                  <audio controls :src="createMultimediaUrl(chat.content.data, chat.content.format)"></audio>
                </div>
                <div v-else-if="chat.data_type.startsWith('video')" class="message-bubble">
                  <video controls :src="createMultimediaUrl(chat.content.data, chat.content.format)" height="70px"></video>
                </div>
                <div v-else-if="chat.data_type.startsWith('image')" class="message-bubble">
                  <img :src="createMultimediaUrl(chat.content.data, chat.content.format)" alt="Image" height="70px" />
                </div>
                <p class="message-info">
                      {{ formatDate(chat.time) }} 
                      <button class='img_'@click="deleteMessage(chat.from_id,chat.time, chat.to_id)">
                        <img :src="'https://cdn.iconscout.com/icon/premium/png-512-thumb/dustbin-1572685-1332560.png'" alt="NONE" height="25px" width="25px" />
                      </button>




                      
                </p>
              </div>



              <div  v-if ="chat.from_id === this.To"  class="message-content1" @mouseover="showDeleteButton=true" @mouseleave="showDeleteButton=false">
                <div v-if="chat.data_type === 'text'" class="message-bubble">
                  <p> {{ decodeBinaryToString(chat.content.data) }}</p>
                </div>
                <div v-else-if="chat.data_type.startsWith('audio')" class="message-bubble">
                  <audio controls :src="createMultimediaUrl(chat.content.data, chat.content.format)"></audio>
                </div>
                <div v-else-if="chat.data_type.startsWith('video')" class="message-bubble">
                  <video controls :src="createMultimediaUrl(chat.content.data, chat.content.format)" height="70px"></video>
                </div>
                <div v-else-if="chat.data_type.startsWith('image')" class="message-bubble">
                  <img :src="createMultimediaUrl(chat.content.data, chat.content.format)" alt="Image" height="70px" />
                </div>
                <p class="message-info">
                      {{ (chat.time) }} 
                      <button class='img_'@click="deleteMessage(chat.from_id, chat.time, chat.to_id)">
                        <img :src="'https://cdn.iconscout.com/icon/premium/png-512-thumb/dustbin-1572685-1332560.png'" alt="NONE" height="25px" width="25px" />
                      </button>




 
                    


                      
                </p>
              </div>


      </div>
    </div>
    <!-- Chat input -->
    <div class="chat-input">
      <label for="fileInput" class="upload-button"><img :src="'https://www.pngplay.com/wp-content/uploads/8/Upload-Icon-Logo-PNG-Clipart-Background.png '" alt="NONE" height="25px" width="25px" />
</label>
      <input id="fileInput" type="file" ref="fileInput" @change="handleFileChange" class="file-input">
      <textarea v-model="message" placeholder="Type your message..." class="message-input"></textarea>
      <button @click="sendMessage" class="send-button">Send</button>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "NewChats",
  data() {
    return {
      chats: [], // Array to store chat messages
      message: "", // Input message
      file: null, // Selected file
      From: "3", // Sender ID
      To: "4", // Receiver ID
      atBottom: true, // Track if user is at the bottom of the chat container
      showDeleteButton: false,
    };
  },
  mounted() {
    console.log(this.$route);
    this.From = this.$route.params.From;
    this.To = this.$route.params.To;

    console.log("From User ID:", this.From);
    console.log("To User ID:", this.To);
    this.getChatData();
  },
  methods: {
    handleFileChange(event) {
      this.file = event.target.files[0];
    },
    scrollToBottom() {
      this.$refs.chatContainer.scrollTop = this.$refs.chatContainer.scrollHeight;
    },
    selectreceiver(reciever){

    },
    getChatData() {
      axios
        .get(`http://localhost:8080/api/chat/${this.To}/${this.From}`)
        .then((response) => {
          this.chats = response.data;
        })
        .catch((error) => {
          console.error("Error fetching chat data:", error);
        });
    },
    formatDate(dateTimeString) {
    const dateTime = new Date(dateTimeString);
    const options = { year: 'numeric', month: 'short', day: 'numeric', hour: 'numeric', minute: 'numeric', hour12: true };
    return dateTime.toLocaleString('en-US', options);
  },
    sendMessage() {
    console.log(new Date().toLocaleString());
      const formData = new FormData();
      formData.append("from_id", this.From);
      formData.append("to_id", this.To);
      formData.append("time", new Date().toLocaleString());
      if (this.file) {
        formData.append("content", this.file);
        formData.append("data_type", this.file.type);
      } else {
        const blob = new Blob([this.message], { type: "text/plain" });
        formData.append("content", blob);
        formData.append("data_type", "text");
      }
      axios
        .post("http://localhost:8080/api/chat", formData)
        .then((response) => {
          console.log("Message sent successfully");

          this.getChatData();
          this.message = "";
          this.file = null;
          this.$refs.fileInput.value = "";
        })
        .catch((error) => {
          console.error("Error sending message:", error);
        });
    },
    decodeBinaryToString(binaryData) {
      const decoder = new TextDecoder();
      const decodedString = decoder.decode(
        new Uint8Array(
          atob(binaryData)
            .split("")
            .map((char) => char.charCodeAt(0))
        )
      );
      return decodedString;
    },
    createMultimediaUrl(binaryData, format) {
      const blob = new Blob([this.base64ToArrayBuffer(binaryData)], {
        type: format,
      });
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
    deleteMessage(from,time,to) {
      console.log(from,time,to);
      axios
        .get(`http://localhost:8080/api/deletechat/${to}/${from}/${time}`)
        .then((response) => {
          this.chats = response.data;
          console.log("DONE DELETION");
          window.location.reload();
        })
        .catch((error) => {
          console.error("Error fetching chat data:", error);
        });
      
    },
  },
};
</script>

<style scoped>
.img_{
  padding:6px;
  background: linear-gradient(to right, #b7bbbf, #bff6f9);
}
.chat-container {
  display: flex;
  flex-direction: column;
  height: 100vh;
}

.chat-messages {
  flex-grow: 1;
  overflow-y: auto;
  padding: 10px;
  background: linear-gradient(to left, #86baea, #d6e8e9); /* Example gradient colors */
 
  margin-bottom: 20px;
}
.message-content{
  display: inline-block;
  padding: 10px;
  border-radius: 20px;
  margin-bottom: 10px;
  position: relative;
  left:100%;
  background: linear-gradient(to right, #b7bbbf, #47bec4);
  border: #ddd;
  flex-direction: right;
  border-radius: 10px;
  justify-self: right;
}
.message {
  margin-bottom: 10px;
  padding: 10px;
  border-radius: 15px;
  max-width: 70%;
}


.message-content1 {
  display: inline-block;
  background: linear-gradient(to right, #b7bbbf, #bff6f9); /* Example gradient colors */
  padding: 10px;
  border-radius: 20px;
  margin-bottom: 10px;
}

.message-bubble {
  background: linear-gradient(to left, #86baea, #d6e8e9); /* Example gradient colors */
  border-radius: 10px;
  padding: 8px 12px;
  margin-bottom: 5px;
}

.message-info {
  font-size: 14px;
  /* color: #3127e6; */
  font-weight: 700;
}

.chat-input {
  display: flex;
  align-items: center;
  padding: 10px;
  border-top: 1px solid #ddd;
}

.upload-button {
  padding: 10px 20px ;
  border: none;
  background: linear-gradient(to right, #b7bbbf, #bff6f9);
  color:  linear-gradient(to left, #86baea, #d6e8e9);
  border-radius: 20px;
  cursor: pointer;
  transition: background-color 0.2s ease;
  justify-self: self-start;
}

.upload-button:hover {
  background-color: #2beae3;
}

.file-input {
  display: none;
}

.message-input {
  flex-grow: 1;
  border: none;
background: linear-gradient(to right, #eceff2, #dedbdb);;
  margin-left: 10px;
  font-size: 14px;
  padding: 10px;
  border-radius: 20px;
}

.send-button {
  border: none;
  background: linear-gradient(to right, #b7bbbf, #bff6f9);
  color:  black;
  padding: 10px 20px;
  border-radius: 20px;
  font-size: 16px;
  font-weight: bold;
font-family: 'Lucida Sans', 'Lucida Sans Regular', 'Lucida Grande', 'Lucida Sans Unicode', Geneva, Verdana, sans-serif;
  cursor: pointer;
}

.send-button:hover {
  background-color: #4f9fff;
}
</style>
