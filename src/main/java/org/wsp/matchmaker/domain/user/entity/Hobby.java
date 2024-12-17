    package org.wsp.matchmaker.domain.user.entity;


    import jakarta.persistence.CascadeType;
    import jakarta.persistence.Column;
    import jakarta.persistence.Entity;
    import jakarta.persistence.GeneratedValue;
    import jakarta.persistence.GenerationType;
    import jakarta.persistence.Id;
    import jakarta.persistence.OneToMany;
    import jakarta.persistence.Table;
    import java.util.HashSet;
    import java.util.Set;
    import lombok.AllArgsConstructor;
    import lombok.Builder;
    import lombok.Getter;
    import lombok.NoArgsConstructor;
    import lombok.Setter;

    @Entity
    @Table(name = "hobby")
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public class Hobby {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "hobby_id", updatable = false, nullable = false)
        private Long hobbyId;

        private String hobbyName;

        @OneToMany(mappedBy = "hobby", cascade = CascadeType.ALL, orphanRemoval = true)
        private Set<UserHobby> userHobbies = new HashSet<>();
    }
