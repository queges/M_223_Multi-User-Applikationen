import axios from "axios";
const API_URL = "http://localhost:8080/";


const login = (username, password) => {
  console.log(username, password);
  return axios
    .post(API_URL + "api/auth/login", { username, password })
    .then((response) => {
      if (response.data.username) {
        localStorage.setItem("user", JSON.stringify(response.data));
        return response.data;
      }
    })
    .catch((error) => {
      console.log(error);
      throw error;
    });
};
/* By doing throw error inside .catch(), you're
allowing the caller to still catch the error.
Otherwise, the promise will resolve with an
error object, which is unexpected behavior for
most consumers.
*/
const logout = () => {
  localStorage.removeItem("user");
};
const getCurrentUser = () => {
  return JSON.parse(localStorage.getItem("user"));
};
const AuthService = {
  login,
  logout,
  getCurrentUser,
};
export default AuthService;
