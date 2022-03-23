import ProductCard from "./ProductCard";


const Products = (productList) => {

    console.log("Loading Products components");
    
    
    const productListData = productList.productList;

    return (
        <>
        { productListData.map( (item) => (
                <ProductCard key={item.id} product={item} />
            )
        )}
        </>
    );

}

export default Products;