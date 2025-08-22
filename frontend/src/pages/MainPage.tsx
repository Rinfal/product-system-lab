// MainPage.tsx
import { useState, useEffect } from "react";
import ProductTable from "../components/ProductTable";
import AddRow from "../components/AddRow";
import {
  getAllProducts,
  getPageProducts,
  getPageQueryProducts,
  getQueryProducts,
} from "../api/productApi";
import Button from "../components/Button";
import type { Product, ProductQuery } from "../dto/ProductDto";

import EditRow from "../components/EditRow";
import SearchRow from "../components/SearchRow";
import Pagination from "../components/Pagination";
import { NavBar } from "../components/NavBar";
const emptyProduct: Product = {
  productId: "",
  productName: "",
  categoryName: "",
  categoryId: 0,
  originPlace: "",
  shippingPlace: "",
  price: 0,
  memberPrice: 0,
  stock: 0,
  updatedBy: "",
  updateTime: "",
  productNote: "",
};

const emptyQuery: ProductQuery = {
  productName: "",
  categoryId: 0,
  originPlace: "",
  shippingPlace: "",
};

function MainPage() {
  const [addRowVisible, setAddRowVisibility] = useState(false);
  const [editRowVisible, setEditRowVisibility] = useState(false);
  const [products, setProducts] = useState<Product[]>([]);
  const [editingProduct, setEditingProduct] = useState<Product>(emptyProduct);
  const [productQuery, setProductQuery] = useState<ProductQuery>(emptyQuery);
  //分页管理
  const [currentPage, setCurrentPage] = useState(1);
  const [totalPages, setTotalPages] = useState(5);
  const pageSize: number = 10;

  const loadProducts = async () => {
    if (productQuery == emptyQuery) {
      const result = await getPageProducts(currentPage, pageSize);
      setProducts(result.list);
    } else {
      loadQueryProducts(productQuery);
    }
  };

  const loadQueryProducts = async (productQuery: ProductQuery) => {
    const result = await getPageQueryProducts(
      productQuery,
      currentPage,
      pageSize
    );
    // console.log("productQuery:" + productQuery);

    // console.log("getQueryProducts:" + result);
    setProducts(result);
  };

  useEffect(() => {
    loadProducts();
    setEditRowVisibility(false);
    console.log("loadProducts");
  }, [currentPage]);

  // useEffect(() => {
  //   loadQueryProducts(productQuery);
  //   console.log("loadQueryProducts");
  // }, [productQuery]);

  //传入：该行商品信息

  return (
    <div className="container">
      <SearchRow
        onClickQuery={(query) => {
          setProductQuery(query);
          loadQueryProducts(query); // ✅ 只在点击时主动查询
          setCurrentPage(1);
        }}
      />
      <div
        className="container"
        style={{
          display: "flex",
          justifyContent: "space-between",
          alignItems: "center",
        }}
      >
        <Pagination
          currentPage={currentPage}
          totalPages={totalPages}
          onPageChange={(page) => setCurrentPage(page)}
        ></Pagination>
        <div
          className="text-primary mx-1"
          style={{
            cursor: "pointer",
            fontSize: "32px", // 这里调大字号
            //fontWeight: "bold",
          }}
          onClick={() => {
            setAddRowVisibility(!addRowVisible);
            if (!addRowVisible) {
              setAddRowVisibility(true);
              setEditRowVisibility(false);
            }
            if (editRowVisible) {
              setEditRowVisibility(false);
              setAddRowVisibility(false);
            }
          }}
        >
          {addRowVisible === true || editRowVisible === true ? "-" : "+"}
        </div>
      </div>
      {addRowVisible && <AddRow onAddSuccess={loadProducts} />}
      {editRowVisible && (
        <EditRow
          onAddSuccess={loadProducts}
          productId={editingProduct.productId}
        />
      )}
      <ProductTable
        products={products}
        pageSize={pageSize}
        currentPage={currentPage}
        onDeleteSuccess={loadProducts}
        onClickEdit={(product) => {
          setEditingProduct(product);
          setEditRowVisibility(true);
          setAddRowVisibility(false);
        }}
      />
    </div>
  );
}

export default MainPage;
