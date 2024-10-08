import { ref } from "vue";
import { defineStore } from "pinia";
import axios from "@/util/http-common";
import { useToast } from "vue-toast-notification";

const toast = useToast();
export const useFavoriteStore = defineStore("favorite", () => {

  //나의책장 - 일반동화
  const myFavoriteTalesList = ref([]);
  const getMyFavoriteTalesList = async function () {
    await axios.get(`/favorites`, { withCredentials: true })
      .then((response) => {
      console.log(response.data)
      myFavoriteTalesList.value = response.data;
      console.log("즐겨찾기목록(store):" + myFavoriteTalesList.value);
    })
    .catch((error) => {
        console.error('에러 발생:', error);
    });
  };

  //즐겨찾기 등록
  const postMyFavoriteTale = (favoriteInfo) => {
    axios.post(`/favorite`, favoriteInfo, { withCredentials: true })
        .then(response => {
            console.log(response.data);
            console.log(userInfo);
        })
        .catch(error => {
            console.error('에러 발생:', error);
        });
  };
  
  //즐겨찾기 삭제
  const deleteMyFavoriteTale = function (taleId) {
    axios.get(`/favorites/${taleId}`, { withCredentials: true })
    .then((response) => {
      const favoriteId = response.data;
      return axios.delete(`/favorites/${favoriteId}`);
    })
    .then(() => {
      toast.success("즐겨찾기가 삭제되었어요.", {
        position: "top",
        duration: 2000,
        queue: true,
        style: {
          backgroundColor: "#f1a8bc",
          color: "white",
        },
      });
    })
    .catch((err) => console.error(err));
  };

  return {
    getMyFavoriteTalesList, myFavoriteTalesList, postMyFavoriteTale, deleteMyFavoriteTale
  };
});