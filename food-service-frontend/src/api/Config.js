class Config {
    SCHEME = process.env.SCHEME ? process.env.SCHEME : "http";
    HOST = process.env.HOST ? process.env.HOST : "localhost";
    PORT = process.env.PORT ? process.env.PORT : "5050";

    PRODUCT_URL = `${this.SCHEME}://${this.HOST}:${this.PORT}/food-items`;

    defaultHeaders() {
        return {
            "Content-Type": "application/json",
            "Accept": "application/json",
        };
    }

    headersWithAuthorization() {
        return {
            ...this.defaultHeaders(),
            Authorization: localStorage.getItem(this.ACCESS_TOKEN),
        };
    }

}

export default Config;