import axios from "axios";
import { showGlobalPopup, usePopup } from "../components/Popup";
import { useNavigate } from "react-router-dom";

// 拦截器给请求加Token
axios.interceptors.request.use((config) => {
  const token = localStorage.getItem("token");
  if (token) {
    config.headers["Authorization"] = token;
  }
  return config;
});

//拦截器处理异常
axios.interceptors.response.use(
  (response) => {
    const res = response.data;

    // 避免 null 的情况
    if (res && res.code !== 0) {
      showGlobalPopup(res.message || "业务错误", "danger");
      return Promise.reject(new Error(res.message || "Error"));
    }

    return response;
  },
  (error) => {

    if (error && error.status === 401) {
       showGlobalPopup("网络错误", "danger");
      window.location.href = "/login";
    }
    // 你愿意的话，这里也可以弹窗
    return Promise.reject(error);
  }
);

