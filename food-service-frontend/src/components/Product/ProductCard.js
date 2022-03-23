import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";

const ProductCard = ({product}) => {

    const navigate = useNavigate();

    const add = async () => {
        console.log("Add button is clicked");
    };
    
    const buy = async () => {
        console.log("Buy button is clicked");
    };


    return (
      <div id={product.id} class="flex justify-center">
        <Link to={`/products/${product.id}`} >
          <div class="flex mr-8 mb-4 mt-4 flex-col md:flex-row md:max-w-xl rounded-lg bg-slate-50 shadow-lg">
            <img class=" w-auto h-auto md:h-48 object-cover md:w-72 rounded-lg" 
              src={product?.imageUrl} 
              alt={product?.name} />
            <div class="p-6 flex flex-col justify-start">
              <h5 class="text-gray-900 text-xl font-medium mb-2">{product?.name}</h5>
              <p class="text-base mb-4 leading-7 font-bold text-emerald-700">
                Price: LKR {product?.price}
              </p>
            </div>
          </div>
        </Link>
      </div>
  );


}

export default ProductCard;