package starwarscantina.player;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "player")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Player.findAll", query = "SELECT p FROM Player p")
    , @NamedQuery(name = "Player.findByPlayerId", query = "SELECT p FROM Player p WHERE p.playerId = :playerId")
    , @NamedQuery(name = "Player.findByPlayerNickname", query = "SELECT p FROM Player p WHERE p.playerNickname = :playerNickname")
    , @NamedQuery(name = "Player.findByPlayerNoclicks", query = "SELECT p FROM Player p WHERE p.playerNoclicks = :playerNoclicks")})
public class Player implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "player_id")
    private Integer playerId;
    @Basic(optional = false)
    @Column(name = "player_nickname")
    private String playerNickname;
    @Basic(optional = false)
    @Column(name = "player_noclicks")
    private String playerNoclicks;

    public Player() {
    }

    public Player(Integer playerId) {
        this.playerId = playerId;
    }

    public Player(Integer playerId, String playerNickname, String playerNoclicks) {
        this.playerId = playerId;
        this.playerNickname = playerNickname;
        this.playerNoclicks = playerNoclicks;
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    public String getPlayerNickname() {
        return playerNickname;
    }

    public void setPlayerNickname(String playerNickname) {
        this.playerNickname = playerNickname;
    }

    public String getPlayerNoclicks() {
        return playerNoclicks;
    }

    public void setPlayerNoclicks(String playerNoclicks) {
        this.playerNoclicks = playerNoclicks;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (playerId != null ? playerId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Player)) {
            return false;
        }
        Player other = (Player) object;
        if ((this.playerId == null && other.playerId != null) || (this.playerId != null && !this.playerId.equals(other.playerId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "starwarscantina.player.Player[ playerId=" + playerId + " ]";
    }
    
}
