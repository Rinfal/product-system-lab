import Button from "./Button";
import SelectDropdown from "./SelectDropdown";

import type { Product, ProductAdding, ProductEditing } from "../dto/ProductDto";
import { useEffect, useState } from "react";
import axios from "axios";
import { convertProductToEditing } from "../hooks/convertProductToAdding";
import { getById } from "../api/productApi";

interface Props {
  //defaultProduct?: Product;
  onAddSuccess: () => void;
  productId: string;
}

const EditRow = ({ onAddSuccess, productId }: Props) => {
  //const productEditingAdding = convertProductToEditing(product);
  const [productEditing, setProductEditing] = useState<ProductEditing>({
    productId: "",
    productName: "",
    categoryId: 0,
    originPlace: "",
    shippingPlace: "",
    price: "",
    memberPrice: "",
    stock: "",
    productNote: "",
  });

  useEffect(() => {
    const fetchAndConvert = async () => {
      const result = await getById(productId); // result 是数据
      const converted = convertProductToEditing(result);

      setProductEditing({
        productId: converted.productId,
        productName: converted.productName,
        categoryId: converted.categoryId || 0,
        originPlace: converted.originPlace,
        shippingPlace: converted.shippingPlace,
        price: converted.price,
        memberPrice: converted.memberPrice,
        stock: converted.stock,
        productNote: converted.productNote,
      });
    };

    fetchAndConvert(); // ⬅️ 别忘了调用它
  }, [productId]);

  const onInputChange = (
    e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>
  ) => {
    const { name, value } = e.target;
    setProductEditing({
      ...productEditing,
      [name]: value,
    });
  };

  const onSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    await axios.put("http://localhost:8080/product/product/update", {
      ...productEditing,
      price: Number(productEditing.price),
      memberPrice: Number(productEditing.memberPrice),
      stock: Number(productEditing.stock),
    });
    onAddSuccess();
  };

  //loadProduct

  const {
    productName,
    categoryId,
    originPlace,
    shippingPlace,
    price,
    memberPrice,
    stock,
    productNote,
  } = productEditing;

  return (
    <div className="container">
      <div className="py-1">
        <form onSubmit={(e) => onSubmit(e)}>
          <table className="table border">
            <thead>
              <tr>
                <th scope="col">商品名</th>
                <th scope="col">商品分类</th>
                <th scope="col">产地</th>
                <th scope="col">发货地</th>
                <th scope="col">价格</th>
                <th scope="col">会员价</th>
                <th scope="col">库存</th>
                <th scope="col">说明</th>
                <th scope="col">操作</th>
              </tr>
            </thead>
            <tbody>
              <tr className="align-middle" style={{ lineHeight: "20px" }}>
                <td>
                  <div>
                    <label
                      htmlFor="productName"
                      className="visually-hidden"
                    ></label>
                    <input
                      type={"text"}
                      className="form-control"
                      placeholder="请输入商品名"
                      name="productName"
                      value={productName}
                      onChange={(e) => onInputChange(e)}
                    />
                  </div>
                </td>
                <td>
                  <div>
                    <label
                      htmlFor="categoryId"
                      className="visually-hidden"
                    ></label>
                    <SelectDropdown
                      name="categoryId"
                      value={categoryId}
                      onChange={(e) => onInputChange(e)}
                    />
                  </div>
                </td>
                <td>
                  <div>
                    <label
                      htmlFor="originPlace"
                      className="visually-hidden"
                    ></label>
                    <input
                      type={"text"}
                      className="form-control"
                      placeholder="请输入发货地址"
                      name="originPlace"
                      value={originPlace}
                      onChange={(e) => onInputChange(e)}
                    />
                  </div>
                </td>
                <td>
                  <div>
                    <label
                      htmlFor="shippingPlace"
                      className="visually-hidden"
                    ></label>
                    <input
                      type={"text"}
                      className="form-control"
                      placeholder="请输入产地"
                      name="shippingPlace"
                      value={shippingPlace}
                      onChange={(e) => onInputChange(e)}
                    />
                  </div>
                </td>
                <td>
                  <div>
                    <label htmlFor="price" className="visually-hidden"></label>
                    <input
                      type={"text"}
                      className="form-control"
                      placeholder="请输入价格"
                      name="price"
                      value={price}
                      onChange={(e) => onInputChange(e)}
                    />
                  </div>
                </td>
                <td>
                  <div>
                    <label
                      htmlFor="memberPrice"
                      className="visually-hidden"
                    ></label>
                    <input
                      type={"text"}
                      className="form-control"
                      placeholder="请输入会员价格"
                      name="memberPrice"
                      value={memberPrice}
                      onChange={(e) => onInputChange(e)}
                    />
                  </div>
                </td>
                <td>
                  <div>
                    <label htmlFor="stock" className="visually-hidden"></label>
                    <input
                      type={"text"}
                      className="form-control"
                      placeholder="请输入库存"
                      name="stock"
                      value={stock}
                      onChange={(e) => onInputChange(e)}
                    />
                  </div>
                </td>
                <td>
                  <div>
                    <label
                      htmlFor="productNote"
                      className="visually-hidden"
                    ></label>
                    <input
                      type={"text"}
                      className="form-control"
                      placeholder="请输入说明"
                      name="productNote"
                      value={productNote}
                      onChange={(e) => onInputChange(e)}
                    />
                  </div>
                </td>
                <td style={{ whiteSpace: "nowrap" }}>
                  <Button onClick={() => console.log("click")} color="primary">
                    更新
                  </Button>
                </td>
              </tr>
            </tbody>
          </table>
        </form>
      </div>
    </div>
  );
};

export default EditRow;
