import { BrowserRouter, Route, Routes } from "react-router-dom";
import Header from "./components/Header";
import Home from "./components/Home";

function App() {
  
  
  
  
  return (
    <div className='flex flex-col min-h-screen h-full'>
      <BrowserRouter>
      <div className='flex-grow flex-shrink-0 p-4'>
            <Header/>
            <Routes>
              <Route path="/" element={<Home/>} />
            </Routes>
          </div>
      </BrowserRouter>
    </div>
  );
}

export default App;
