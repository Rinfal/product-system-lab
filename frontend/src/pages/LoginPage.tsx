import axios from "axios";
import { useEffect, useState } from "react";
import type { UserRegistering } from "../dto/UserDto";
import { useNavigate } from "react-router-dom";
import { usePopup } from "../components/Popup";
import type { ApiResponse } from "../api/productApi";

function LoginPage() {
  const [userRegistering, setUserRegistering] = useState<UserRegistering>({
    userName: "",
    userPassword: "",
  });
  const [showSuccessPopup, setShowSuccessPopup] = useState(false);
  //接受Popup组件最后return的showPopup
  const { showPopup } = usePopup();

  let navigate = useNavigate();

  //进入LoginPage清空Token和userName，并使Navbar更新
  useEffect(() => {
    localStorage.removeItem("token");
    localStorage.removeItem("userName");
    window.dispatchEvent(new Event("userNameUpdated")); // 通知NavBar更新
  }, []);

  const onInputChange = (
    e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>
  ) => {
    const { name, value } = e.target;
    setUserRegistering({
      ...userRegistering,
      [name]: value,
    });
  };

  const onSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    const nativeSubmitter = (e.nativeEvent as SubmitEvent)
      .submitter as HTMLButtonElement;
    const action = nativeSubmitter?.value;

    if (action === "register") {
      await axios.post("http://localhost:8080/auth/user/add", {
        ...userRegistering,
      });
      showPopup("注册成功！", "success");
    } else if (action === "login") {
      // 登录逻辑
      await axios
        .post("http://localhost:8080/auth/user/login", {
          ...userRegistering,
        })
        .then((res) => {
          console.log(res);
          if (res.data.code === 0) {
            const token = res.data.data;
            // 保存 token 到 localStorage 或 sessionStorage
            localStorage.setItem("token", token);
            localStorage.setItem("userName", userName);
            window.dispatchEvent(new Event("userNameUpdated"));
            // 后续请求统一带上 Authorization 头
            // 这里有bug，虽然localStorage是持久的，但是defaults设置不是持久的，刷新就没了
            //axios.defaults.headers.common["Authorization"] = token;

            // 跳转或提示
            showPopup("登录成功！", "success");
            navigate("/");
          }
        });
    }
  };

  const { userName, userPassword } = userRegistering;
  return (
    <div className="container">
      <div className="row">
        <div
          className="border rounded p-5 mt-5 shadow mx-auto d-flex flex-column"
          style={{ width: "400px", minHeight: "450px" }}
        >
          {showSuccessPopup && (
            <div
              className="position-fixed top-50 start-50 translate-middle alert alert-success shadow"
              style={{ zIndex: 1055 }}
            >
              注册成功！
            </div>
          )}
          <form onSubmit={(e) => onSubmit(e)}>
            <h2 className="text-center m-4">注册/登录</h2>
            <div className="mb-3">
              <label htmlFor="Name" className="form-label mb-0 mt-3">
                Name
              </label>
              <input
                type={"text"}
                className="form-control"
                placeholder="请输入用户名"
                name="userName"
                value={userName}
                onChange={(e) => onInputChange(e)}
              />

              <label htmlFor="Password" className="form-label mb-0 mt-3">
                Password
              </label>
              <input
                type={"text"}
                className="form-control"
                placeholder="请输入密码"
                name="userPassword"
                value={userPassword}
                onChange={(e) => onInputChange(e)}
              />
            </div>
            <div className="d-flex justify-content-center mt-auto pt-4">
              <button
                className="btn btn-success mx-2"
                type="submit"
                name="action"
                value="register"
              >
                注册
              </button>
              <button
                className="btn btn-primary mx-2"
                type="submit"
                name="action"
                value="login"
              >
                登录
              </button>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
}

export default LoginPage;
