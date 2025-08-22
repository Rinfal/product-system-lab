import axios from "axios";
import type { Product, ProductPage, ProductQuery } from "../dto/ProductDto";
import type { OrderVo } from "../dto/OrderPojo";


export interface ApiResponse<T> {
  code: number;
  message: string;
  data: T;
}

export const getPageProducts = async (pageNum:number,pageSize:number): Promise<ProductPage> => {
  const response = await axios.get<ApiResponse<ProductPage>>(
    "http://localhost:8080/product/product/getPage",{params: { pageNum, pageSize }}
  );

  return response.data.data;
};

export const getAllProducts = async (): Promise<Product[]> => {
  const response = await axios.get<ApiResponse<Product[]>>(
    "http://localhost:8080/product/product/getAll",
  );

  return response.data.data;
};

export const getPageQueryProducts = async (productQuery:ProductQuery,pageNum:number,pageSize:number): Promise<Product[]> => {
  const response = await axios.post<ApiResponse<ProductPage>>(
    "http://localhost:8080/product/product/search",productQuery,{params: { pageNum, pageSize }}
  );

  return response.data.data.list;
};

export const getQueryProducts = async (productQuery:ProductQuery): Promise<Product[]> => {
  const response = await axios.post<ApiResponse<Product[]>>(
    "http://localhost:8080/product/product/search",productQuery
  );

  return response.data.data;
};


export const deleteProduct = (productId:string) => {
  return axios.delete("http://localhost:8080/product/product/delete/"+productId
    );
};

export const getById =async(productId:string) =>{

  const response = await axios.get<ApiResponse<Product>>(
    "http://localhost:8080/product/product/getById/"+productId
  );
  console.log(response);
  return response.data.data;
}

export const createOrder =async(productId:string) =>{

  const response = await axios.post<ApiResponse<OrderVo>>(
    "http://localhost:8080/order/order/create",{productId}
  );
  console.log(response);
  return response.data.data;
}

export const getUserName =async() =>{

  const response = await axios.get<ApiResponse<string>>(
    "http://localhost:8080/auth/user/getUserName"
  );
  console.log(response);
  return response.data.data;
}



