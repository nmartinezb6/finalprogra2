package org.example.DBTodito;

import org.example.models.CursosEntity;
import org.example.models.EstudiantesEntity;
import org.example.models.InscripcionesEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.Date;
import java.util.List;

public class DBTodito {

    public void GrabarCurso(String nombreCurso, String profeso) {
        SessionFactory sessionFactory =
                new Configuration()
                        .configure()
                        .buildSessionFactory();
        Session session = sessionFactory.openSession();
        if (session != null) {
            System.out.println("Session Correcta");

            try {
                session.beginTransaction();
                CursosEntity curso = new CursosEntity();
                curso.setNombreCurso(nombreCurso);
                curso.setProfesor(profeso);
                session.save(curso);
                session.getTransaction().commit();
                sessionFactory.close();


            } catch (Exception e) {
                sessionFactory.close();
                System.out.println(e.getMessage());
            }


        } else {
            System.out.println("Error al abrir la conexion");
        }
    }

    public void UpdateCurso(int id, String nombreCurso, String profeso) {
        SessionFactory sessionFactory =
                new Configuration()
                        .configure()
                        .buildSessionFactory();
        Session session = sessionFactory.openSession();
        if (session != null) {
            System.out.println("Session Correcta");

            try {
                session.beginTransaction();
                CursosEntity curso = session.get(CursosEntity.class, id);
                curso.setNombreCurso(nombreCurso);
                curso.setProfesor(profeso);
                session.save(curso);
                session.getTransaction().commit();
                sessionFactory.close();


            } catch (Exception e) {
                sessionFactory.close();
                System.out.println(e.getMessage());
            }


        } else {
            System.out.println("Error al abrir la conexion");
        }
    }

    public void DeleteCurso(int id) {
        SessionFactory sessionFactory =
                new Configuration()
                        .configure()
                        .buildSessionFactory();
        Session session = sessionFactory.openSession();
        if (session != null) {
            System.out.println("Session Correcta");

            try {
                session.beginTransaction();
                CursosEntity curso = session.get(CursosEntity.class, id);
                session.delete(curso);
                session.getTransaction().commit();
                sessionFactory.close();


            } catch (Exception e) {
                sessionFactory.close();
                System.out.println(e.getMessage());
            }


        } else {
            System.out.println("Error al abrir la conexion");
        }
    }

    public List<CursosEntity> ListCursos() {
        SessionFactory sessionFactory =
                new Configuration()
                        .configure()
                        .buildSessionFactory();
        Session session = sessionFactory.openSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<CursosEntity> cq = cb.createQuery(CursosEntity.class);
        Root<CursosEntity> rootEntry = cq.from(CursosEntity.class);
        CriteriaQuery<CursosEntity> all = cq.select(rootEntry);

        TypedQuery<CursosEntity> allQuery = session.createQuery(all);
        return allQuery.getResultList();
    }

    public void GrabarEstuidante(String nombre, String apellido, String email) {
        SessionFactory sessionFactory =
                new Configuration()
                        .configure()
                        .buildSessionFactory();
        Session session = sessionFactory.openSession();
        if (session != null) {
            System.out.println("Session Correcta");

            try {
                session.beginTransaction();
                EstudiantesEntity estudiante = new EstudiantesEntity();
                estudiante.setNombre(nombre);
                estudiante.setApellido(apellido);
                estudiante.setEmail(email);
                session.save(estudiante);
                session.getTransaction().commit();
                sessionFactory.close();


            } catch (Exception e) {
                sessionFactory.close();
                System.out.println(e.getMessage());
            }


        } else {
            System.out.println("Error al abrir la conexion");
        }
    }

    public void UpdateEstudiante(int id, String nombre, String apellido, String email) {
        SessionFactory sessionFactory =
                new Configuration()
                        .configure()
                        .buildSessionFactory();
        Session session = sessionFactory.openSession();
        if (session != null) {
            System.out.println("Session Correcta");

            try {
                session.beginTransaction();
                EstudiantesEntity estudiante = session.get(EstudiantesEntity.class, id);
                estudiante.setNombre(nombre);
                estudiante.setApellido(apellido);
                estudiante.setEmail(email);
                session.save(estudiante);
                session.getTransaction().commit();
                sessionFactory.close();


            } catch (Exception e) {
                sessionFactory.close();
                System.out.println(e.getMessage());
            }


        } else {
            System.out.println("Error al abrir la conexion");
        }
    }

    public void DeleteEstudiante(int id) {
        SessionFactory sessionFactory =
                new Configuration()
                        .configure()
                        .buildSessionFactory();
        Session session = sessionFactory.openSession();
        if (session != null) {
            System.out.println("Session Correcta");

            try {
                session.beginTransaction();
                EstudiantesEntity estudiante = session.get(EstudiantesEntity.class, id);
                session.delete(estudiante);
                session.getTransaction().commit();
                sessionFactory.close();


            } catch (Exception e) {
                sessionFactory.close();
                System.out.println(e.getMessage());
            }


        } else {
            System.out.println("Error al abrir la conexion");
        }
    }

    public List<EstudiantesEntity> ListEstudiantes() {
        SessionFactory sessionFactory =
                new Configuration()
                        .configure()
                        .buildSessionFactory();
        Session session = sessionFactory.openSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<EstudiantesEntity> cq = cb.createQuery(EstudiantesEntity.class);
        Root<EstudiantesEntity> rootEntry = cq.from(EstudiantesEntity.class);
        CriteriaQuery<EstudiantesEntity> all = cq.select(rootEntry);

        TypedQuery<EstudiantesEntity> allQuery = session.createQuery(all);
        return allQuery.getResultList();
    }

        public void GrabarInscripcion(int idEstudiante, int idCurso) {
            SessionFactory sessionFactory =
                    new Configuration()
                            .configure()
                            .buildSessionFactory();
            Session session = sessionFactory.openSession();
            if (session != null) {
                System.out.println("Session Correcta");

                try {
                    session.beginTransaction();
                    InscripcionesEntity inscripcion = new InscripcionesEntity();
                    inscripcion.setEstudiantes(session.get(EstudiantesEntity.class, idEstudiante));
                    inscripcion.setCursos(session.get(CursosEntity.class, idCurso));
                    long millis = System.currentTimeMillis();
                    inscripcion.setFechaInscripcion(new Date(millis));
                    session.save(inscripcion);
                    session.getTransaction().commit();
                    sessionFactory.close();


                } catch (Exception e) {
                    sessionFactory.close();
                    System.out.println(e.getMessage());
                }


            } else {
                System.out.println("Error al abrir la conexion");
            }
        }

        public void UpdateInscripcion(int id, int idEstudiante, int idCurso) {
            SessionFactory sessionFactory =
                    new Configuration()
                            .configure()
                            .buildSessionFactory();
            Session session = sessionFactory.openSession();
            if (session != null) {
                System.out.println("Session Correcta");

                try {
                    session.beginTransaction();
                    InscripcionesEntity inscripcion = session.get(InscripcionesEntity.class, id);
                    inscripcion.setEstudiantes(session.get(EstudiantesEntity.class, idEstudiante));
                    inscripcion.setCursos(session.get(CursosEntity.class, idCurso));
                    long millis = System.currentTimeMillis();
                    inscripcion.setFechaInscripcion(new Date(millis));
                    session.save(inscripcion);
                    session.getTransaction().commit();
                    sessionFactory.close();


                } catch (Exception e) {
                    sessionFactory.close();
                    System.out.println(e.getMessage());
                }


            } else {
                System.out.println("Error al abrir la conexion");
            }
        }

        public void DeleteInscripcion(int id) {
            SessionFactory sessionFactory =
                    new Configuration()
                            .configure()
                            .buildSessionFactory();
            Session session = sessionFactory.openSession();
            if (session != null) {
                System.out.println("Session Correcta");

                try {
                    session.beginTransaction();
                    InscripcionesEntity curso = session.get(InscripcionesEntity.class, id);
                    session.delete(curso);
                    session.getTransaction().commit();
                    sessionFactory.close();


                } catch (Exception e) {
                    sessionFactory.close();
                    System.out.println(e.getMessage());
                }


            } else {
                System.out.println("Error al abrir la conexion");
            }
        }

        public List<InscripcionesEntity> ListInscripciones() {
            SessionFactory sessionFactory =
                    new Configuration()
                            .configure()
                            .buildSessionFactory();
            Session session = sessionFactory.openSession();

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<InscripcionesEntity> cq = cb.createQuery(InscripcionesEntity.class);
            Root<InscripcionesEntity> rootEntry = cq.from(InscripcionesEntity.class);
            CriteriaQuery<InscripcionesEntity> all = cq.select(rootEntry);

            TypedQuery<InscripcionesEntity> allQuery = session.createQuery(all);
            //TypedQuery<InscripcionesEntity> allQuery = session.createQuery("from InscripcionesEntity i");
            return allQuery.getResultList();
        }
    }

