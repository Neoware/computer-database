package com.excilys.formation.entity;

import java.time.LocalDate;

/**
 * Computer entity corresponding to the computer table in database.
 * 
 * @author neoware
 *
 */
public class Computer {
	private Long id;
	private String name;
	private LocalDate introduced;
	private LocalDate discontinued;
	private Company computerCompany;

	public static class Builder {
		private String name;
		private Long id;
		private LocalDate introduced;
		private LocalDate discontinued;
		private Company computerCompany;

		public Builder() {
		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder id(Long id) {
			this.id = id;
			return this;
		}

		public Builder introduced(LocalDate introduced) {
			this.introduced = introduced;
			return this;
		}

		public Builder discontinued(LocalDate discontinued) {
			this.discontinued = discontinued;
			return this;
		}

		public Builder computerCompany(Company computerCompany) {
			this.computerCompany = computerCompany;
			return this;
		}

		public Computer build() {
			return new Computer(this);
		}
	}

	private Computer(Builder computerBuilder) {
		this.id = computerBuilder.id;
		this.name = computerBuilder.name;
		this.introduced = computerBuilder.introduced;
		this.discontinued = computerBuilder.discontinued;
		this.computerCompany = computerBuilder.computerCompany;
	}

	public static Builder getBuilder() {
		return new Builder();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getIntroduced() {
		return introduced;
	}

	public void setIntroduced(LocalDate introduced) {
		this.introduced = introduced;
	}

	public LocalDate getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(LocalDate discontinued) {
		this.discontinued = discontinued;
	}

	public Company getComputerCompany() {
		return computerCompany;
	}

	public void setComputerCompany(Company computerCompany) {
		this.computerCompany = computerCompany;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((computerCompany == null) ? 0 : computerCompany.hashCode());
		result = prime * result + ((discontinued == null) ? 0 : discontinued.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((introduced == null) ? 0 : introduced.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Computer other = (Computer) obj;
		if (computerCompany == null) {
			if (other.computerCompany != null) {
				return false;
			}
		} else if (!computerCompany.equals(other.computerCompany)) {
			return false;
		}
		if (discontinued == null) {
			if (other.discontinued != null) {
				return false;
			}
		} else if (!discontinued.equals(other.discontinued)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (introduced == null) {
			if (other.introduced != null) {
				return false;
			}
		} else if (!introduced.equals(other.introduced)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Computer [id=" + id + ", name=" + name + ", introduced=" + introduced + ", discontinued=" + discontinued
				+ ", computerCompany=" + computerCompany + "]";
	}

}
