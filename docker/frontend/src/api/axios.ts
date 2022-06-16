import axios from "axios";

//TMDBからのbaseURLリクエストを作成
export const instance = axios.create({
  baseURL: "http://18.176.21.110:8080/api",
  headers: { "Content-Type": "application/json" },
  responseType: "json",
});
