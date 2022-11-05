package com.angjm.almacenapp.model.dto;



import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "authorities")
public class Authority {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id_authority")
    private int id;

    @Column
    private String authority;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "authorities")
    Set<Usuario> usuarios;
}
