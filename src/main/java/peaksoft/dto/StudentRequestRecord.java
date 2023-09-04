package peaksoft.dto;


public record StudentRequestRecord(
        String firstName,
        String lastName,
        int age,
        String email
) {
}
