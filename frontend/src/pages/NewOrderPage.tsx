import { useEffect, useState } from "react";
import type { OrderVo } from "../dto/OrderPojo";
import { createOrder } from "../api/productApi";
import { useLocation, useNavigate, useParams } from "react-router-dom";

function NewOrderPage() {
  let navigate = useNavigate();

  //Gpt写的，可以不用set直接初始化
  const location = useLocation();
  const initialOrder = location.state as OrderVo | undefined;
  const [orderVo, setOrderVo] = useState<OrderVo | undefined>(initialOrder);
  const [timeLeft, setTimeLeft] = useState<number | undefined>(undefined);
  //const { productId } = useParams<{ productId: string }>();

  //实时更新
  useEffect(() => {
    if (!orderVo?.expireTime) return;

    const target = new Date(orderVo.expireTime).getTime();
    const timer = setInterval(() => {
      setTimeLeft(target - Date.now());
    }, 1000);

    return () => clearInterval(timer);
  }, [orderVo?.expireTime]);

  //GPT写的，我还没看
  const formatTimeLeft = (ms: number | undefined) => {
    if (ms === undefined) return "加载中...";
    if (ms <= 0) return "已过期";

    const totalSeconds = Math.floor(ms / 1000);
    const minutes = Math.floor(totalSeconds / 60);
    const seconds = totalSeconds % 60;

    const pad = (n: number) => n.toString().padStart(2, "0");
    return `${pad(minutes)}:${pad(seconds)}`;
  };

  function formatOrderTime(time?: string): string {
    if (!time) return "";
    const date = new Date(time);
    const day = date.getDate(); // 不补0
    const monthNames = [
      "Jan",
      "Feb",
      "Mar",
      "Apr",
      "May",
      "Jun",
      "Jul",
      "Aug",
      "Sep",
      "Oct",
      "Nov",
      "Dec",
    ];
    const month = monthNames[date.getMonth()];
    const year = date.getFullYear();
    const hour = date.getHours().toString().padStart(2, "0");
    const minute = date.getMinutes().toString().padStart(2, "0");

    return `${day} ${month} ${year} ${hour}:${minute}`;
  }

  return (
    <div className="container my-5" style={{ maxWidth: "500px" }}>
      {/* 返回 */}
      <div
        className="text-primary mb-3"
        style={{ cursor: "pointer" }}
        onClick={() => navigate(-1)}
      >
        ← 返回
      </div>

      {/* 标题 */}
      <h4 className="fw-bold mb-4 text-center">Order details</h4>

      {/* 商品卡片 */}
      <div className="card mb-4">
        <div className="card-body">
          <div className="d-flex align-items-center mb-3">
            <img
              src="https://placehold.co/96x96/eeeeee/aaaaaa/svg"
              className="rounded me-3"
              alt="App Icon"
            />
            <div>
              <h6 className="mb-0">{orderVo?.productName}</h6>
              <small className="text-muted">
                ¥{orderVo?.unitPrice?.toFixed(2)}
              </small>
              <br />
              <small className="text-muted">{orderVo?.userName}</small>
              <br />
              <small className="text-muted">
                {orderVo?.orderTime ? formatOrderTime(orderVo.orderTime) : ""}
              </small>
            </div>
          </div>

          <hr />

          <div className="d-flex justify-content-between">
            <span>Order Amount</span>
            <span>{orderVo?.orderAmount}</span>
          </div>
          <div className="d-flex justify-content-between fw-bold mt-2">
            <span>Total</span>
            <span>¥{orderVo?.totalPrice?.toFixed(2)}</span>
          </div>
          <div className="d-flex justify-content-between mt-2">
            <span>Order Status</span>
            <span>{orderVo?.orderStatusView}</span>
          </div>
        </div>
      </div>

      {/* 订单信息 */}
      <div className="card mb-4">
        <div className="card-body">
          <div className="d-flex justify-content-between mb-2">
            <span className="text-muted">Payment Time Left</span>
            <span>{formatTimeLeft(timeLeft)}</span>
          </div>
          <div className="d-flex justify-content-between">
            <span className="text-muted">Order ID</span>
            <span className="text-monospace fw-semibold">
              {orderVo?.orderId}
            </span>
          </div>
        </div>
      </div>

      {/* 更多操作 */}
      <div className="text-center">
        <button className="btn btn-link text-primary">Resend Receipt</button>
        <br />
        <button className="btn btn-link text-primary">Report a Problem</button>
      </div>
    </div>
  );
}

export default NewOrderPage;
