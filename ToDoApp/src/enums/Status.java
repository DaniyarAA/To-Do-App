package enums;

public enum Status {
    NEW("Новый"){
        @Override
        public void changeStatus() {

        }

        @Override
        public void changeDescription() {

        }

        @Override
        public void deleteTask() {

        }
    },
    IN_PROGRESS("В процессе"){

        @Override
        public void changeStatus() {

        }

        @Override
        public void changeDescription() {

        }

        @Override
        public void deleteTask() {

        }
    },
    DONE("Сделано"){
        @Override
        public void changeStatus() {

        }

        @Override
        public void changeDescription() {

        }

        @Override
        public void deleteTask() {

        }
    };
    private final String description;

    Status(String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public abstract void changeStatus();
    public abstract void changeDescription();
    public abstract void deleteTask();
}
