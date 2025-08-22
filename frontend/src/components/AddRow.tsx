import Button from "./Button";
import SelectDropdown from "./SelectDropdown";

import type { ProductAdding } from "../dto/ProductDto";
import { useState } from "react";
import axios from "axios";

interface Props {
  //defaultProduct?: Product;
  onAddSuccess: () => void;
}

const AddRow = ({ onAddSuccess }: Props) => {
  const [productAdding, setProductAdding] = useState<ProductAdding>({
    productName: "",
    categoryId: 0,
    originPlace: "",
    shippingPlace: "",
    price: "",
    memberPrice: "",
    stock: "",
    productNote: "",
  });

  const onInputChange = (
    e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>
  ) => {
    const { name, value } = e.target;
    setProductAdding({
      ...productAdding,
      [name]: value,
    });
  };

  const onSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    await axios.post("http://localhost:8080/product/product/add", {
      ...productAdding,
      price: Number(productAdding.price),
      memberPrice: Number(productAdding.memberPrice),
      stock: Number(productAdding.stock),
    });
    onAddSuccess();
  };
  const {
    productName,
    categoryId,
    originPlace,
    shippingPlace,
    price,
    memberPrice,
    stock,
    productNote,
  } = productAdding;

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
                      placeholder="输入商品名"
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
                      placeholder="输入发货地址"
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
                      placeholder="输入产地"
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
                      placeholder="输入价格"
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
                      placeholder="输入会员价"
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
                      placeholder="输入库存"
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
                      placeholder="输入说明"
                      name="productNote"
                      value={productNote}
                      onChange={(e) => onInputChange(e)}
                    />
                  </div>
                </td>

                <td style={{ width: "120px" }}>
                  <div className="md-end">
                    <Button
                      onClick={() => console.log("click")}
                      color="primary"
                    >
                      新增
                    </Button>
                  </div>
                </td>
              </tr>
            </tbody>
          </table>
        </form>
      </div>
    </div>
  );
};

export default AddRow;
