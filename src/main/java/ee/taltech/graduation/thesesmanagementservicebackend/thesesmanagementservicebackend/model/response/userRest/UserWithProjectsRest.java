package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.response.userRest;

import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.response.projectRest.ProjectRest;

import java.util.List;

public class UserWithProjectsRest {

        private String userId;
        private String firstName;
        private String lastName;
        private String email;
        private String graduationLevel;
        private String starterTeam;
        private String confirmedProject;
        private List<ProjectRest> projects;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public List<ProjectRest> getProjects() {
            return projects;
        }

        public void setProjects(List<ProjectRest> projects) {
            this.projects = projects;
        }

        public String getGraduationLevel() {
            return graduationLevel;
        }

        public void setGraduationLevel(String graduationLevel) {
            this.graduationLevel = graduationLevel;
        }

        public String getStarterTeam() {
            return starterTeam;
        }

        public void setStarterTeam(String starterTeam) {
            this.starterTeam = starterTeam;
        }

        public String getConfirmedProject() {
            return confirmedProject;
        }

        public void setConfirmedProject(String confirmedProject) {
            this.confirmedProject = confirmedProject;
        }
}






