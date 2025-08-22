import { useEffect, useState } from "react";
import type { OrderVo } from "../dto/OrderPojo";
import { createOrder } from "../api/productApi";
import { useLocation, useNavigate, useParams } from "react-router-dom";

function OrderPage() {
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
    return `${minutes}分${seconds}秒`;
  };

  return (
    <div className="container">
      <div className="row">
        <div
          className="border rounded p-5 mt-5 shadow mx-auto d-flex flex-column"
          style={{ width: "400px", minHeight: "450px" }}
        >
          <h2 className="text-center m-4">商品订单</h2>
          <div className="mb-3">
            <ul className="list-group">
              <li className="list-group-item">
                订单状态：{orderVo?.orderStatusView}
              </li>
              <li className="list-group-item">
                购买数：{orderVo?.orderAmount}
              </li>
              <li className="list-group-item">订单编号：{orderVo?.orderId}</li>
              <li className="list-group-item">用户名：{orderVo?.userName}</li>
              <li className="list-group-item">
                购买商品：{orderVo?.productName}
              </li>
              <li className="list-group-item">
                订单创建时间：{orderVo?.orderTime}
              </li>
              <li className="list-group-item">
                订单剩余时间：{formatTimeLeft(timeLeft)}
              </li>
            </ul>
          </div>
          <div className="d-flex justify-content-center mt-auto pt-4">
            <button
              className="btn btn-success mx-2"
              type="submit"
              name="action"
              value="register"
              onClick={() => {
                navigate("/");
              }}
            >
              已支付
            </button>
          </div>
        </div>
      </div>
    </div>
  );
}

export default OrderPage;
