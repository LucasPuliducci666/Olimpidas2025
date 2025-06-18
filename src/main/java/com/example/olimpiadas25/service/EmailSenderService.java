package com.example.olimpiadas25.service;

import com.example.olimpiadas25.persistence.entity.*;
import com.example.olimpiadas25.persistence.repository.PedidoHistoricoRepository;
import com.example.olimpiadas25.persistence.repository.PedidoRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class EmailSenderService {

    private final JavaMailSender mailSender;
    private final PedidoHistoricoRepository pedidoHistoricoRepository;
    private final PedidoRepository pedidoRepository;
    private final PagoService pagoService;

    @Value("${mail.from}")
    private String from;

    @Value("${mail.from.name}")
    private String fromName;

    @Value("${mail.subject.confirmacion}")
    private String subjectConfirmacion;

    @Value("${mail.subject.anulacion}")
    private String subjectAnulacion;

    public void enviarConfirmacionPedido(String destinatario, String nombre, PedidoEntity pedido) {
        StringBuilder paquetesHtml = new StringBuilder();
        for (PaquetEntity paquete : pedido.getPaquetes()) {
            paquetesHtml.append("- ").append(paquete.getNombre())
                    .append(" ($").append(paquete.getPrecio()).append(")<br>");
        }

        String asunto = subjectConfirmacion;
        String cuerpo = "<h1>Hola " + nombre + ",</h1>"
                + "<p>Tu pedido nº<strong>" + pedido.getId() + "</strong> fue <b>confirmado</b> exitosamente.</p>"
                + "<p>Reservaste:</p><p><strong>" + paquetesHtml + "</strong></p>"
                + "<p>Recordá la Fecha!  Del: <strong>" + pedido.getFechainic() + "</strong> hasta el: <strong>" + pedido.getFechafin() + "</strong></p>"
                + "<p>Gracias por elegirnos!</p>";

        enviarCorreo(destinatario, asunto, cuerpo);
    }

    public void enviarAnulacionPedido(String destinatario, String nombre, PedidoEntity pedido) {
        String asunto = subjectAnulacion;
        String cuerpo = "<h1>Hola " + nombre + ",</h1>"
                + "<p>Tu pedido nº<strong>" + pedido.getId() + "</strong> fue <b>anulado</b>.</p>"
                + "<p>Anulaste: <strong>" + pedido.getPaquetes() + "</strong></p>"
                + "<p>Si este no fuiste vos, contactate con el soporte de inmediato.</p>";
        enviarCorreo(destinatario, asunto, cuerpo);
    }

    private void enviarCorreo(String destinatario, String asunto, String cuerpoHtml) {
        try {
            MimeMessage mensaje = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mensaje, true);
            helper.setTo(destinatario);
            helper.setSubject(asunto);
            helper.setText(cuerpoHtml, true);
            helper.setFrom(from, fromName);
            mailSender.send(mensaje);
        } catch (MessagingException e) {
            throw new RuntimeException("Error al enviar correo: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Error general: " + e.getMessage());
        }
    }

    @Transactional
    public PedidoEntity entregarPedido(Integer id) {
        PedidoEntity pedido = pedidoRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
        pedido.setEstado(Estado.entregado);
        enviarConfirmacionPedido(
                pedido.getCliente().getEmail(),
                pedido.getCliente().getNombre(),
                pedido

        );

        return pedido;
    }

    @Transactional
    public PedidoEntity anularPedido(Integer id) {
        PedidoEntity pedido = pedidoRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
        pedido.setEstado(Estado.cancelado);
        enviarAnulacionPedido(
                pedido.getCliente().getEmail(),
                pedido.getCliente().getNombre(),
                pedido
        );
        return pedido;
    }
}




