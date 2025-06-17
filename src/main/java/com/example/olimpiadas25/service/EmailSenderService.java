package com.example.olimpiadas25.service;

import com.example.olimpiadas25.persistence.entity.Estado;
import com.example.olimpiadas25.persistence.entity.PedidoEntity;
import com.example.olimpiadas25.persistence.entity.PedidoHistoricoEntity;
import com.example.olimpiadas25.persistence.entity.VentaEntity;
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
        String asunto = subjectConfirmacion;
        String cuerpo = "<h1>Hola " + nombre + ",</h1>"
                + "<p>Tu pedido nº<strong>" + pedido.getId() + "</strong> fue <b>confirmado</b> exitosamente.</p>"
                + "<p>Reservasta en el Hotel: <strong>" + pedido.getPaquete().getUbicacion() + "</strong></p>"
                + "<p>Para la Fecha del: <strong>" + pedido.getFechainic() + "</strong> hasta el: <strong>" + pedido.getFechafin() + "</strong></p>"
                + "<p>Gracias por elegirnos!</p>";
        enviarCorreo(destinatario, asunto, cuerpo);
    }

    public void enviarAnulacionPedido(String destinatario, String nombre, PedidoEntity pedido) {
        String asunto = subjectAnulacion;
        String cuerpo = "<h1>Hola " + nombre + ",</h1>"
                + "<p>Tu pedido nº<strong>" + pedido.getId() + "</strong> fue <b>anulado</b>.</p>"
                + "<p>Ubicacion: <strong>" + pedido.getPaquete().getUbicacion() + "</strong></p>"
                + "<p>Fecha: del <strong>" + pedido.getFechainic() + "</strong> al <strong>" + pedido.getFechafin() + "</strong></p>"
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
        pagoService.registrarVenta(pedido, VentaEntity.MedioPago.TARJETA_CREDITO);

        PedidoHistoricoEntity historico = new PedidoHistoricoEntity();
        historico.setFechainic(LocalDate.from(pedido.getFechainic()));
        historico.setFechafin(LocalDate.from(pedido.getFechafin()));
        historico.setPrecio(pedido.getPaquete().getPrecio());
        historico.setEstado(PedidoHistoricoEntity.Estado.entregado);
        historico.setCliente(pedido.getCliente());
        historico.setPaquete(pedido.getPaquete());

        pedidoHistoricoRepository.save(historico);

        pedidoRepository.delete(pedido);

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




