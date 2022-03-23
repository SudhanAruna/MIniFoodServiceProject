import { useEffect, useState } from "react";
import { useParams } from "react-router";
import ProductService from "../../api/ProductService";
import Products from "./Product";

const ProductList = () => {


    const { categoryId } = useParams();
    const [productList, setProductList] = useState();
    const [message, setMessage] = useState("Loading...");

    const productService = new ProductService();

    
    useEffect( () => {
        async function fetchProducts() {
            console.log("Fetching the products");
            var res = null;
            if (categoryId){
                console.log("Fetching product list with id: "+categoryId);
                res = await productService.fetchAllByCategoryId(categoryId);    
            } else {
                console.log("Fetching product list : "+categoryId);
                res = await productService.fetchList();
            }
            
            if (res && res.sucess) {
                console.log("Successful data retrival");
                setProductList(res.data);
            }
            console.log("Failed to retrival all products data");
            setMessage(res);
        }
        fetchProducts();
    }, []);


    return (
        <div className="max-w-7xl mx0auto px-4 sm:px-6 lg:px-8">
            { productList ? (
                <div className="flex flex-wrap -mx-1 lg:-mx-4">
                    <Products productList={productList ? productList: []}/>
                </div>
            ): (
                <div className="text-lg font-semibold">{message}</div>
            )}
        </div>
    );


}

export default ProductList;