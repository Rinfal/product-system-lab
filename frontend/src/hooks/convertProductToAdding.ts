import type { Product, ProductAdding, ProductEditing } from "../dto/ProductDto";

export function convertProductToEditing(c: Product): ProductEditing {
  return {
    productId:c.productId,
    productName: c.productName,
    categoryId: c.categoryId,
    originPlace: c.originPlace,
    shippingPlace: c.shippingPlace,
    price: c.price.toString(),
    memberPrice: c.memberPrice.toString(),
    stock: c.stock.toString(),
    productNote: c.productNote,
  };
}