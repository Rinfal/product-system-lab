import { createContext, useContext, useState, type ReactNode } from "react";

const PopupContext = createContext<PopupContextType>({
  showPopup: () => {}, // 提供一个空函数作为默认值
});

type PopupContextType = {
  showPopup: (message: string, type?: string, duration?: number) => void;
};

export const usePopup = () => useContext(PopupContext);

export let showGlobalPopup = (
  message: string,
  type = "success",
  duration = 1500
) => {};

export const PopupProvider = ({ children }: { children: ReactNode }) => {
  const [popup, setPopup] = useState({
    show: false,
    message: "",
    type: "success",
  });

  const showPopup = (message: any, type = "success", duration = 1500) => {
    setPopup({ show: true, message, type });
    setTimeout(() => {
      setPopup({ show: false, message: "", type });
    }, duration);
  };

  showGlobalPopup = showPopup;

  return (
    <PopupContext.Provider value={{ showPopup }}>
      {children}
      {popup.show && (
        <div
          className={`position-fixed top-50 start-50 translate-middle alert alert-${popup.type} shadow`}
          style={{ zIndex: 1055 }}
        >
          {popup.message}
        </div>
      )}
    </PopupContext.Provider>
  );
};
