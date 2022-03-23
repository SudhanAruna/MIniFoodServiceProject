import Config from "./Config";

class ProductService {

    constructor() {
        this.config = new Config();
    }

    handleError(error) {
        const err = new Map([
            [TypeError, "There was a problem fetching the response."],
            [SyntaxError, "There was a problem parsing the response."],
            [Error, error.message],
        ]).get(error.constructor);

        console.log(err);

        return error;
    }


    async fetchList() {

        console.log("Called for fetching all product");

        return fetch(
            this.config.PRODUCT_URL,
            {
                method: "GET",
                mode: "cors",
                headers: {
                    ...this.config.defaultHeaders(),
                },
            })
            .then((response) => Promise.all([response, response.json()]))
            .then( ([response, json]) => {
                
                if (!response.ok) {
                    console.log("Get Response FAILED: Status: "+response.status);        
                    return { sucess: false, error: json };
                }

                console.log("Get response OK");
                return { sucess: true, data: json };
                
            }).catch( (ex) => {
                console.log("Exception Message \n" +ex.message);
                return this.handleError(ex);
                
            })
    }


    async fetch(productId) {
        console.log("Fetching product with specific id :"+ productId);
        
        return fetch(
            this.config.PRODUCT_URL + "/" + productId, {
                method: "GET",
                mode: "cors",
                headers: {
                    ...this.config.defaultHeaders(),
                },
            }).then((response) => Promise.all([response, response.json()]))
            .then(([response, json]) => {
                if (!response.ok) {
                    console.log("Get Response FAILED: Status: "+response.status);
                    return { success: false, error: json };
                }
                console.log("Get Response OK");
                return { success: true, data: json};
            })
            .catch( (ex) => {
                console.log("Exception Message \n" +ex.message);
                this.handleError(ex);
            });
    }


    async fetchAllByCategoryId(categoryId) {
        console.log("Fetching all product with category id :"+ categoryId);
        
        return fetch(
            this.config.PRODUCT_URL + "/categories/" + categoryId, {
                method: "GET",
                mode: "cors",
                headers: {
                    ...this.config.defaultHeaders(),
                },
            }).then((response) => Promise.all([response, response.json()]))
            .then(([response, json]) => {
                if (!response.ok) {
                    console.log("Get Response FAILED: Status: "+response.status);
                    return { sucess: false, error: json };
                }
                console.log("Get Response OK");
                return { sucess: true, data: json};
            })
            .catch( (ex) => {
                console.log("Exception Message \n" +ex.message);
                this.handleError(ex);
            });
    }

    async fetchCategoryList() {

        console.log("Called for fetching all product");

        return fetch(
            this.config.PRODUCT_URL + "/categories",
            {
                method: "GET",
                mode: "cors",
                headers: {
                    ...this.config.defaultHeaders(),
                },
            })
            .then((response) => Promise.all([response, response.json()]))
            .then( ([response, json]) => {
                
                if (!response.ok) {
                    console.log("Get Response FAILED: Status: "+response.status);        
                    return { success: false, error: json };
                }

                console.log("Categories Get response OK");
                return { success: true, data: json };
                
            }).catch( (ex) => {
                console.log("Exception Message \n" +ex.message);
                return this.handleError(ex);
                
            })
    }



}


export default ProductService;