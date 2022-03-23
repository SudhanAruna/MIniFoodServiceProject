import { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";

import ProductService from "../../api/ProductService";

const ProductDetail = () => {

    const { id } = useParams();
    const [product, setProduct] = useState();


    const productService = new ProductService();


    useEffect( () => {
        async function getProductDetail(id) {
            const response = await productService.fetch(id);

            if (response && response.success) {
                setProduct(response.data);               
            }
        }
        getProductDetail(id);
    }, [id]);

    const add = async () => {
        console.log("Add button is clicked");
      };
    
    
      const buy = async () => {
        console.log("Buy button is clicked");
    };


    return (
        <div id={product?.id} className="max-w-3xl w-42 mx-auto px-4 sm:px-6 lg:px-9 my-1 w-full md:w-1/2 lg:my-4">

          <figure className="bg-slate-100 rounded-3xl p-8 md:p-0 xs:flex md:block shadow-2xl">
            <img
              className="w-full h-72 mx-auto rounded-2xl"
              src={product?.imageUrl}
              alt={product?.name}
            />
            <div className="pt-4 md:p-6 text-center xs:pl-2 md:text-left space-y-4">
              <form className="flex-auto">
                <div className="flex flex-wrap items-center justify-between">
                  <h1 className="w-full flex-none font-bold mb-2.5 text-left text-3xl">
                    <Link to="/">{product?.name}</Link>
                  </h1>
                  <div className="text-2xl leading-7 font-bold text-emerald-700">
                    {"LKR "}
                    {product?.price}
                  </div>
                  <div className="text-sm font-medium text-gray-400 ml-3">
                    In stock
                  </div>
                </div>
                <div className="font-semibold text-gray-500 pt-8">{product?.description}</div>
    
                
                {product && product.tag ? (
                  <div className="font-semibold py-8 flex flex-row flex-wrap">
                    {" "}
                    {product?.tag?.map((t) => (
                      <div>
                        <span className="bg-yellow-400 p-2 rounded-2xl">
                          {t.name}{" "}
                        </span>
                        &nbsp;
                      </div>
                    ))}{" "}
                  </div>
                ) : (
                  ""
                )} 
               
                
                <div className="flex space-x-3 mt-8 mb-4 text-sm font-semibold">
                  <div className="flex-auto flex justify-between">
                    <button
                      className="w-1/2 flex items-center justify-center text-2xl rounded-full bg-green-500 text-white py-2 md:w-1/4"
                      type="button"
                      onClick={buy}
                    >
                      Buy now
                    </button>
                    <button
                      className="flex items-center justify-center rounded-full text-2xl text-green-500"
                      type="button"
                      onClick={add}
                    >
                      Add Cart
                    </button>
                  </div>
                </div> 
               
                <p className="text-sm text-gray-500 text-left">
                  Free shipping on all local orders.
                </p>
              </form>
            </div>
          </figure>
          </div> 
    );


}

export default ProductDetail;