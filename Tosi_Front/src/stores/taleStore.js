import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import router from '@/router'
import axios from "@/util/http-common";

export const useTaleStore = defineStore('tale', () => {
  //동화 목록
  const taleList = ref([])
  const getTaleList = function () {
    axios.get(`/tales`)
      .then((response) => {
        console.log(response.data)
        taleList.value = response.data
      })
      .catch((error) => {
        console.error('Error fetching tales:', error)
      })
  }

  //동화 검색
  const searchTaleByTitle = function (title) {
    axios.get(`/search`, {
      params: { title }
    })
      .then((response) => {
        taleList.value = response.data
    }).catch((error) => {
      console.error('Error fetching search:', error)
    })
  }

  return { taleList, getTaleList, searchTaleByTitle }
})