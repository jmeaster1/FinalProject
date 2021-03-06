package entities;


import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Week {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private Date date;
    
    private int gameWeek;
    
	@JsonIgnore
	@OneToMany(mappedBy = "week")//(cascade= {CascadeType.PERSIST, CascadeType.REMOVE})
	private List<Game> games;

	@JsonIgnore
	@ManyToMany(mappedBy="weeks")
	private List<League> leagues;
	
    public List<Game> getGames() {
		return games;
	}

	public void setGames(List<Game> games) {
		this.games = games;
	}

	public int getGameWeek() {
		return gameWeek;
	}

	public void setGameWeek(int gameWeek) {
		this.gameWeek = gameWeek;
	}

	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

	public List<League> getLeagues() {
		return leagues;
	}

	public void setLeagues(List<League> leagues) {
		this.leagues = leagues;
	}

	public Week(int id, Date date) {
		super();
		this.id = id;
		this.date = date;
	}

	public Week() {
		super();
	}



	@Override
	public String toString() {
		return "Week [id=" + id + ", date=" + date + ", gameWeek=" + gameWeek + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Week other = (Week) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (id != other.id)
			return false;
		return true;
	}
    
    
    
    
}