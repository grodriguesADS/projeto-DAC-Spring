package br.edu.ifpb.dac.guilherme.projetojpa.presentation.dto;

public class TokenDTO {
	
	    private String token;
	    private OwnerDTO user;
	    
	    public TokenDTO(String token, OwnerDTO userDTO) {
	        this.token = token;
	        this.user = userDTO;
	    }
	    public TokenDTO() {
	    }
	    
		public String getToken() {
			return token;
		}
		public void setToken(String token) {
			this.token = token;
		}
		public OwnerDTO getUser() {
			return user;
		}
		public void setUser(OwnerDTO user) {
			this.user = user;
		}
	    
	    
}
