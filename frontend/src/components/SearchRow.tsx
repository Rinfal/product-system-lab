import Button from "./Button";
import SelectDropdown from "./SelectDropdown";

import type { Product, ProductAdding, ProductQuery } from "../dto/ProductDto";
import { useState } from "react";
import axios from "axios";
import { getQueryProducts } from "../api/productApi";

interface Props {
  //defaultProduct?: Product;
  onClickQuery: (productQuery: ProductQuery) => void;
}

const SearchRow = ({ onClickQuery }: Props) => {
  const [productQuery, setProductQuery] = useState<ProductQuery>({
    productName: "",
    categoryId: 0,
    originPlace: "",
    shippingPlace: "",
  });

  const onInputChange = (
    e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>
  ) => {
    const { name, value } = e.target;
    setProductQuery({
      ...productQuery,
      [name]: value,
    });
  };

  const onSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    onClickQuery(productQuery);
  };
  const { productName, categoryId, originPlace, shippingPlace } = productQuery;

  return (
    <div className="container">
      <div className="py-1">
        <form onSubmit={(e) => onSubmit(e)}>
          <table className="table border mb-0">
            <thead>
              <tr>
                <th scope="col">商品名</th>
                <th scope="col">商品分类</th>
                <th scope="col">产地</th>
                <th scope="col">发货地</th>
                <th scope="col"></th>
              </tr>
            </thead>
            <tbody>
              <tr className="align-middle" style={{ lineHeight: "20px" }}>
                <td>
                  <div className="mb-0">
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
                  <div className="mb-0">
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
                  <div className="mb-0">
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
                  <div className="mb-0">
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
                <td className="align-middle">
                  <Button onClick={() => console.log("click")} color="primary">
                    查询
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

export default SearchRow;
