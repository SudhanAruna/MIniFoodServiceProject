import { BrowserRouter, Route, Routes } from "react-router-dom";
import Header from "./components/Header";
import Home from "./components/Home";
import ProductDetail from "./components/Product/ProductDetail";
import ProductList from "./components/Product/ProductList";

function App() {
  
  
  
  
  return (
    <div className='flex flex-col min-h-screen h-full'>
      <BrowserRouter>
      <div className='flex-grow flex-shrink-0 p-4'>
            <Header/>
            <Routes>
              <Route path="/" element={<Home/>} />
              <Route path="/category/:categoryId" element={ <ProductList/>} />
              <Route path="/products/:id" element={ <ProductDetail/> } />
            </Routes>
          </div>
      </BrowserRouter>
    </div>
  );
}

export default App;
