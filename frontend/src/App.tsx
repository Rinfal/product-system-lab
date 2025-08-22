import MainPage from "./pages/MainPage";
import LoginPage from "./pages/LoginPage";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import "./utils/TokenInterceptor.ts";
import OrderPage from "./pages/OrderPage.tsx";
import { PopupProvider } from "./components/Popup.tsx";
import { NavBar } from "./components/NavBar.tsx";
import NewOrderPage from "./pages/NewOrderPage.tsx";
import NewNavbar from "./components/NewNavBar.tsx";
import { useEffect, useState } from "react";
import { getUserName } from "./api/productApi.ts";

function App() {
  return (
    <Router>
      <PopupProvider>
        <NewNavbar />
        <div className="App">
          <Routes>
            <Route path="/" element={<MainPage />} />
            <Route path="/login" element={<LoginPage />} />
            <Route path="/order/:productId" element={<NewOrderPage />} />
          </Routes>
        </div>
      </PopupProvider>
    </Router>
  );
}

export default App;
