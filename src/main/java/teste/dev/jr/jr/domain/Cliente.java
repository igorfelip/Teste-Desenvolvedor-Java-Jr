package teste.dev.jr.jr.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private Long cpf;
    private Double saldo;

}
