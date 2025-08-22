import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

export default function NewNavbar() {
  const [userName, setUserName] = useState("未登录");

  let navigate = useNavigate();

  useEffect(() => {
    const syncUserName = () => {
      const name = localStorage.getItem("userName") || "未登录";
      setUserName(name);
    };

    // 初始化加载
    syncUserName();

    // 监听 localStorage 改变
    window.addEventListener("storage", syncUserName);
    window.addEventListener("userNameUpdated", syncUserName); // 兼容自定义事件

    return () => {
      window.removeEventListener("storage", syncUserName);
      window.removeEventListener("userNameUpdated", syncUserName);
    };
  }, []);

  return (
    <nav
      className="navbar navbar-expand-lg"
      style={{
        height: "58px",
        background: "linear-gradient(to right, #fdfdfd, #f5f5f5)",
        borderBottom: "1px solid #eaeaea",
        boxShadow: "0 2px 4px rgba(0,0,0,0.05)",
      }}
    >
      <div className="container-fluid px-4">
        <span
          className="navbar-brand fw-semibold ms-5"
          style={{ cursor: "pointer", fontSize: "1.75rem" }}
        >
          🧾 商品列表
        </span>

        <button
          className="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarFruity"
          aria-controls="navbarFruity"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span className="navbar-toggler-icon"></span>
        </button>

        <div
          className="collapse navbar-collapse justify-content-end"
          id="navbarFruity"
        >
          <ul className="navbar-nav mb-2 mb-lg-0">
            {userName !== "未登录" && (
              <li className="nav-item">
                <a
                  className="nav-link text-decoration-underline"
                  style={{ cursor: "pointer" }}
                  onClick={() => {
                    localStorage.removeItem("token");
                    localStorage.removeItem("userName");
                    navigate("/login");
                  }}
                >
                  退出登录
                </a>
              </li>
            )}
            <li className="nav-item">
              <a className="nav-link text-decoration-underline">
                {userName ? userName : "未登陆"}
              </a>
            </li>
            <li className="nav-item">
              <a className="nav-link">订单</a>
            </li>
            <li className="nav-item">
              <a className="nav-link">帮助</a>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  );
}
