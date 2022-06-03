import axios from "axios";

//TMDBからのbaseURLリクエストを作成
export const instance = axios.create({
  baseURL: "http://localhost:8080/api",
});
