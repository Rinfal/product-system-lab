import React from "react";

export interface Product {
  productId: string; // 商品唯一标识（可选列）
  productName: string; // 商品名
  categoryName: string; // 商品分类ID（如需展示分类名，可以考虑加个 categoryName）
  categoryId: number;
  originPlace: string; // 产地
  shippingPlace: string; // 发货地
  price: number; // 价格
  memberPrice: number; // 会员价
  stock: number; // 库存
  updatedBy: string; // 更新人
  updateTime: string; // 更新时间，推荐用 ISO 字符串（如 "2025-07-25T12:00:00"）
  productNote: string;
}


export interface ProductAdding {
  productName: string;
  categoryId: number;
  originPlace: string;
  shippingPlace: string;
  price: string;
  memberPrice: string;
  stock: string;
  productNote: string;
}

export interface ProductEditing {
  productId: string; // 商品唯一标识（可选列）
  productName: string;
  categoryId: number;
  originPlace: string;
  shippingPlace: string;
  price: string;
  memberPrice: string;
  stock: string;
  productNote: string;
}

export interface ProductQuery {
  productName: string;
  categoryId: number;
  originPlace: string;
  shippingPlace: string;
}

export interface ProductPage {
  total:number;
  list:Product[];
}
