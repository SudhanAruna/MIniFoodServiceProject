import { useState } from "react";
import { Link } from "react-router-dom";

const ProductCard = ({product}) => {

    const add = async () => {
        console.log("Add button is clicked");
    };
    
    const buy = async () => {
        console.log("Buy button is clicked");
    };


    return (
        <div id={product.id} className="my-1 px-1 w-full md:w-1/2 lg:my-4 lg:w-1/4">
        <figure className="bg-slate-50 shadow-xl rounded-3xl p-8 md:p-0 xs:flex md:block">
            <img
            className="w-full h-48 object-cover mx-auto rounded-3xl"
            src={product.imageUrl}
            alt={product.name}
            />
        <div className="pt-4 md:p-6 text-center xs:pl-2 md:text-left space-y-4">
          <form className="flex-auto">
            <div className="flex flex-wrap items-center justify-between">
              <h1 className="w-full text-2xl font-bold mb-2.5 text-left">
                <Link to={`/products/${product.id}`}>{product.name}</Link>
              </h1>
              <div className="text-xl leading-7 font-bold text-emerald-700 ">
                {"$"}
                { product.price }
              </div>
              <div className="text-sm font-medium text-gray-400 ml-3">
                In stock
              </div>
            </div>
            <div className="flex space-x-3 mt-8 mb-4 text-sm font-semibold">
              <div className="flex-auto flex justify-between">
                <button
                  className="w-1/2 flex items-center justify-center rounded-full bg-emerald-700 text-white py-2"
                  type="button"
                  onClick={buy}
                >
                  Buy now
                </button>
                <button
                  className="flex items-center justify-center rounded-full bg-green-50 text-emerald-700"
                  type="button"
                  onClick={add}
                >
                  Add to bag
                </button>
              </div>
            </div>
          </form>
        </div>
      </figure>
    </div>
  );


}

export default ProductCard;