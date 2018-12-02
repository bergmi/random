public enum Drug {

        ASPIRIN("As", "Aspirin"),
        ANTIBIOTICS("An", "Antibiotics"),
        INSULIN("I", "Insulin"),
        PARACETAMOL("P", "Paracetamol"),
        NO_DRUG("", "");

        private String drug;
        private String fullStringRepresentation;

        Drug(String drug, String fullStringRepresentation) {
            this.drug = drug;
            this.fullStringRepresentation = fullStringRepresentation;
        }

        static Drug fromString(String d) {
            for (Drug existingDrug : Drug.values()) {
                if (existingDrug.shortRep().equalsIgnoreCase(d))
                    return existingDrug;
            }

            throw new IllegalArgumentException("No drug found corresponding to input argument: " + d);
        }

        static String validInputArguments() {
            StringBuilder builder = new StringBuilder();

            for (int i = 0; i < Drug.values().length; ++i) {
                builder.append(Drug.values()[i].shortRep()).append(": ").append(Drug.values()[i].fullRep()).append(", ");
            }

            builder.delete(builder.length() - 2, builder.length());

            return builder.toString();
        }

    public String shortRep() {
        return this.drug;
    }

    public String fullRep() {
        return this.fullStringRepresentation;
    }

}
