import { defineStore } from "pinia";


export const useCookieStore = defineStore("cookie", () => {
  
function getCookieValue(key) {
    const cookies = document.cookie.split(';');
    for (let i = 0; i < cookies.length; i++) {
      const cookie = cookies[i].trim();
      if (cookie.startsWith(key + '=')) {
        return cookie.substring(key.length + 1);
      }
    }
    return null; // 해당 키를 찾지 못한 경우
  }
  
  function setCookie(name, value, days) {
    const expirationDate = new Date();
    expirationDate.setDate(expirationDate.getDate() + days); // 유효기간을 days일 후로 설정
    const cookieString = `${name}=${value}; expires=${expirationDate.toUTCString()}; path=/`;
    document.cookie = cookieString;
  }

  function deleteCookie(name) {
    document.cookie = name + "=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
  }  

  return {
    getCookieValue, setCookie, deleteCookie
  }
});