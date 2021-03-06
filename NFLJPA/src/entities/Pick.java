package entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Pick {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @OneToOne
	@JoinColumn(name="teamId")
	private Team team;
    
    @JsonIgnore
    @Column(name="isCorrect")
    private Boolean correct;
    
    @OneToOne
	@JoinColumn(name="gameId")
	private Game game;
    
    
    @ManyToOne
	@JoinColumn(name="userId")
	private User user;

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public boolean isCorrect() {
		return correct;
	}

	public void setCorrect(boolean correct) {
		this.correct = correct;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public Pick(int id, Team team, boolean correct, Game game, User user) {
		super();
		this.id = id;
		this.team = team;
		this.correct = correct;
		this.game = game;
		this.user = user;
	}

	public Pick() {
		super();
	}

	@Override
	public String toString() {
		return "Pick [id=" + id + ", team=" + team + ", isCorrect=" + correct + ", game=" + game + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((game == null) ? 0 : game.hashCode());
		result = prime * result + id;
		result = prime * result + (correct ? 1231 : 1237);
		result = prime * result + ((team == null) ? 0 : team.hashCode());
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
		Pick other = (Pick) obj;
		if (game == null) {
			if (other.game != null)
				return false;
		} else if (!game.equals(other.game))
			return false;
		if (id != other.id)
			return false;
		if (correct != other.correct)
			return false;
		if (team == null) {
			if (other.team != null)
				return false;
		} else if (!team.equals(other.team))
			return false;
		return true;
	}
  
}