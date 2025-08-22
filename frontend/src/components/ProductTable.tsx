import React, { useState, useEffect } from "react";
import Button from "./Button";
import type { Product } from "../dto/ProductDto";
import { createOrder, deleteProduct } from "../api/productApi";
import { useNavigate } from "react-router-dom";
import "../OperationButton.css";

interface Props {
  products: Product[];
  currentPage: number;
  pageSize: number;
  onDeleteSuccess: () => void;
  onClickEdit: (commodit: Product) => void;
}

const ProductTable = ({
  products,
  currentPage,
  pageSize,
  onDeleteSuccess,
  onClickEdit,
}: Props) => {
  let navigate = useNavigate();

  const onDelete = async (productId: string) => {
    await deleteProduct(productId);
    onDeleteSuccess();
  };

  const onPurchase = async (productId: string) => {
    const orderVo = await createOrder(productId);
    navigate(`/order/${orderVo.orderId}`, { state: orderVo });
  };

  return (
    <div className="container">
      <div className="py-1">
        <table className="table border">
          <thead>
            <tr>
              <th scope="col">序号</th>
              <th scope="col">商品名</th>
              <th scope="col">商品分类</th>
              <th scope="col">产地</th>
              <th scope="col">发货地</th>
              <th scope="col">价格</th>
              <th scope="col">会员价</th>
              <th scope="col">库存</th>
              <th scope="col">更新人</th>
              <th scope="col">更新时间</th>
              <th scope="col">操作</th>
            </tr>
          </thead>
          <tbody>
            {products.length === 0 && (
              <tr>
                <td
                  colSpan={12}
                  style={{ textAlign: "center", lineHeight: "40px" }}
                >
                  暂无数据
                </td>
              </tr>
            )}
            {products.map((product: Product, index) => (
              <tr key={product.productId} className="align-middle">
                <th scope="row">{index + 1 + pageSize * (currentPage - 1)}</th>
                <td>{product.productName}</td>
                <td>{product.categoryName}</td>
                <td>{product.originPlace}</td>
                <td>{product.shippingPlace}</td>
                <td>{product.price.toFixed(2)}</td>
                <td>{product.memberPrice.toFixed(2)}</td>
                <td>{product.stock}</td>
                <td>{product.updatedBy}</td>
                <td>{product.updateTime}</td>
                <td>
                  <Button
                    onClick={() => onClickEdit(product)}
                    color="primary"
                    //codeColor="#007AFF"
                  >
                    编辑
                  </Button>
                  <Button
                    onClick={() => onPurchase(product.productId)}
                    //onClick={() => console.log(product.productId)}
                    color="success"
                  >
                    购买
                  </Button>
                  <Button
                    onClick={() => onDelete(product.productId)}
                    //onClick={() => console.log(product.productId)}
                    color="danger"
                    //codeColor="#FF3B30"
                  >
                    删除
                  </Button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default ProductTable;
