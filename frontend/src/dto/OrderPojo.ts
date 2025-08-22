export interface OrderVo {
  orderId: string;
  // 展示用的订单状态中文描述，比如 “已支付”
  orderStatusView: string;
  // 已格式化的下单时间字符串，如 "2025-08-04 15:30:00"
  orderTime: string;
  expireTime:string;
  orderAmount: number;
  userName: string;
  productName:string;
  unitPrice:number;
  totalPrice:number;
}
