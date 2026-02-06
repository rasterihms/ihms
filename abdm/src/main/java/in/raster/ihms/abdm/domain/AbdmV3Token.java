package in.raster.ihms.abdm.domain;

/**
 * Custom object for ABDM tokens.
 */
public class AbdmV3Token {

    private String token;
    private String expiresIn;
    private String refreshToken;
    private String refreshExpiresIn;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(String expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getRefreshExpiresIn() {
        return refreshExpiresIn;
    }

    public void setRefreshExpiresIn(String refreshExpiresIn) {
        this.refreshExpiresIn = refreshExpiresIn;
    }

    @Override
    public String toString() {
        return "AbdmV3Token{" +
            "token='" + token + '\'' +
            ", expiresIn='" + expiresIn + '\'' +
            ", refreshToken='" + refreshToken + '\'' +
            ", refreshExpiresIn='" + refreshExpiresIn + '\'' +
            '}';
    }
}
