import axios from "axios";
import { useEffect, useState } from "react";

interface Props {
  name: string;
  value: string | number;
  onChange: (e: React.ChangeEvent<HTMLSelectElement>) => void;
}

const SelectDropdown = ({ name, value, onChange }: Props) => {
  const [categories, setCategories] = useState<Category[]>([]);

  useEffect(() => {
    loadCategories();
  }, []);

  const loadCategories = async () => {
    const result = await axios.get("http://localhost:8080/product/category");
    setCategories(result.data.data);
  };

  interface Category {
    categoryName: string;
    categoryCode: string;
    categoryId: number;
  }

  return (
    <select
      className="form-select"
      //aria-label="Small select example"
      name={name}
      value={value}
      onChange={onChange}
    >
      <option value="">请选择分类</option>
      {categories.map((category: Category) => (
        <option value={category.categoryId}>{category.categoryName}</option>
      ))}
    </select>
  );
};

export default SelectDropdown;
