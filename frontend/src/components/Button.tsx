import React from "react";
interface Props {
  children: string;
  onClick: () => void;
  color?: string;
  codeColor?: string;
}
const Button = ({ children, onClick, color = "primary", codeColor }: Props) => {
  return (
    <button
      className={"btn btn-" + color + " mx-2 "}
      onClick={onClick}
      style={{ whiteSpace: "nowrap" }}
    >
      {children}
    </button>
  );
};

export default Button;
